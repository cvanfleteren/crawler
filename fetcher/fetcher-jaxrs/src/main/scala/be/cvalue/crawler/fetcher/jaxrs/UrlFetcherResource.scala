package be.cvalue.crawler.fetcher.jaxrs

import javax.ws.rs.{QueryParam, GET, Path}
import javax.ws.rs.container.{AsyncResponse, Suspended}
import javax.validation.constraints.{Size, NotNull}

@Path("/hello")
trait UrlFetcherResource {

  @GET
  def fetch(@NotNull @Size(min=5) @QueryParam("url") url:String, @Suspended asyncResponse:AsyncResponse) : Unit

}
