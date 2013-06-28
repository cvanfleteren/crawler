package be.cvalue.crawler.fetcher.impl.ning

import com.ning.http.client._
import scala.concurrent.{Future, Promise}
import be.cvalue.crawler.fetcher.api.UrlFetcher
;

class AsyncHttpClientUrlFetcher(client:AsyncHttpClient) extends UrlFetcher {

  def fetch(url:String) : Future[String] = {
    val p = Promise[String]

    client.prepareGet(url).execute(new AsyncCompletionHandler[Promise[String]] {

      override def onCompleted(response: Response): Promise[String] = {

        p.success(response.getResponseBody)

      }
    })

    return p.future;
  }

}
