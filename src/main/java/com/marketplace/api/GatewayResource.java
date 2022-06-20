package com.marketplace.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.net.URLConnection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

@Path("/")
public class GatewayResource {

  private static final String FALLBACK_RESOURCE = "/META-INF/resources/index.html";
  private static final Map<String, String> EXTENSION_TYPES = Map.of(
    "svg", "image/svg+xml"
  );
  private final ApiResource apiResource;

  @Inject
  public GatewayResource(ApiResource apiResource) {
    this.apiResource = apiResource;
  }

  @Path("/api/")
  public ApiResource getApiResource() {
    return apiResource;
  }

  @GET
  @Path("/")
  public Response getFrontendRoot() throws IOException {
    return getFrontendStaticFile("index.html");
  }

  @GET
  @Path("/{fileName:.+}")
  public Response getFrontendStaticFile(@PathParam("fileName") String fileName) throws IOException {
    final InputStream requestedFileStream = GatewayResource.class.getResourceAsStream("/META-INF/resources/" + fileName);
    final InputStream inputStream;
    final String fileToServe;
    if (requestedFileStream != null) {
      fileToServe = fileName;
      inputStream = requestedFileStream;
    } else {
      fileToServe = FALLBACK_RESOURCE;
      inputStream = GatewayResource.class.getResourceAsStream(FALLBACK_RESOURCE);
    }
    final StreamingOutput streamingOutput = outputStream -> IOUtils.copy(inputStream, outputStream);
    return Response
      .ok(streamingOutput)
      .cacheControl(CacheControl.valueOf("max-age=900"))
      .type(contentType(inputStream, fileToServe))
      .build();
  }

  private String contentType(InputStream inputStream, String file) throws IOException {
    return EXTENSION_TYPES.getOrDefault(
      FilenameUtils.getExtension(file),
      URLConnection.guessContentTypeFromStream(inputStream)
    );
  }
}