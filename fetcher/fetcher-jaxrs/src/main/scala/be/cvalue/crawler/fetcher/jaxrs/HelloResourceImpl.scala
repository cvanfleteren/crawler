package be.cvalue.crawler.fetcher.jaxrs

import javax.ws.rs.{GET, Path}

@Path("/hello")
class HelloResourceImpl extends  HelloResource {

  @GET
  def hello() = {
    "Hello"
  }

}
