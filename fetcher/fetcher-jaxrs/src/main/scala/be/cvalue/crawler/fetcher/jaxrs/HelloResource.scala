package be.cvalue.crawler.fetcher.jaxrs

import javax.ws.rs.{GET, Path}

@Path("/hello")
trait HelloResource {

  @GET
  def hello():String

}
