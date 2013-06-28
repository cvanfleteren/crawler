package be.cvalue.crawler.fetcher.mbean

trait FetchUrlMBean {
  
  def fetch(url:String, timeoutInSeconds:Int) : String

}