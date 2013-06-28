package be.cvalue.crawler.fetcher.api

import scala.concurrent.Future

trait UrlFetcher {

  def fetch(url:String) : Future[String]

}
