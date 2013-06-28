package be.cvalue.crawler.fetcher.impl.ning

import scala.concurrent.Await
import scala.concurrent.duration._
import com.ning.http.client.{AsyncHttpClient, AsyncHttpClientConfigBean}
import org.testng.annotations.Test
import org.testng.Assert

@Test
class UrlFetcherTest {


  def testHappyPath() {
    val config: AsyncHttpClientConfigBean = new AsyncHttpClientConfigBean()
    config.setCompressionEnabled(true)
    config.setRedirectEnabled(true)

    val client = new AsyncHttpClient(config)

    val f = new AsyncHttpClientUrlFetcher(client).fetch("http://www.google.com")

    val res = Await.ready(f, 5 seconds).value
    val content = res.get.get

    Assert.assertTrue(content.contains("google"))

  }

}
