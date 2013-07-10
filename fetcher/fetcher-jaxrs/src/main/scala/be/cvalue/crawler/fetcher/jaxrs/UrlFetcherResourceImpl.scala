package be.cvalue.crawler.fetcher.jaxrs

import javax.ws.rs.{PathParam, GET, Path}
import be.cvalue.crawler.fetcher.api.UrlFetcher
import javax.ws.rs.container.{Suspended, AsyncResponse}
import java.util.concurrent.TimeUnit
import scala.concurrent.{ExecutionContext}
import scala.concurrent.duration._
import ExecutionContext.Implicits.global

class UrlFetcherResourceImpl(urlFetcher:UrlFetcher) extends  UrlFetcherResource {


  def fetch(url:String, asyncResponse:AsyncResponse) : Unit = {
    asyncResponse.setTimeout(10, TimeUnit.SECONDS)

    val f = urlFetcher.fetch(url)

    f onSuccess {
      case s => asyncResponse.resume(s)
    }
    f onFailure {
      case x:Throwable => asyncResponse.resume(x)
    }
  }

}
