package be.cvalue.crawler.fetcher.jaxrs

import be.cvalue.crawler.fetcher.api.UrlFetcher
import javax.ws.rs.container.AsyncResponse
import java.util.concurrent.TimeUnit
import scala.concurrent.ExecutionContext
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
