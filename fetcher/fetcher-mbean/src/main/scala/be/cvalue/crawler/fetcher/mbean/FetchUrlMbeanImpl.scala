package be.cvalue.crawler.fetcher.mbean

import javax.management.StandardMBean
import be.cvalue.crawler.fetcher.api.UrlFetcher
import scala.concurrent.Await
import scala.concurrent.duration._

class FetchUrlMbeanImpl() extends StandardMBean(classOf[FetchUrlMBean]) with FetchUrlMBean {

  private[this] var urlFetcher:UrlFetcher = null

	def fetch(url:String, timeoutInSeconds:Int) : String = {
    if(urlFetcher == null) {
      return "No UrlFetcher found"
    }

	  return Await.result(urlFetcher.fetch(url),timeoutInSeconds seconds)
	}

  def setUrlFetcher(urlFetcher:UrlFetcher) : Unit = {
      this.urlFetcher = urlFetcher
  }

}