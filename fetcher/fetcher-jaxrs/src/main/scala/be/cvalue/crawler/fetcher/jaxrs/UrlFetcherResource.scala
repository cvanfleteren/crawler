package be.cvalue.crawler.fetcher.jaxrs

import javax.ws.rs.{QueryParam, GET, Path}
import javax.ws.rs.container.{AsyncResponse, Suspended}

@Path("/hello")
trait UrlFetcherResource {

  @GET
  def fetch(@QueryParam("url") url:String, @Suspended asyncResponse:AsyncResponse) : Unit

}
