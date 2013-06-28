package be.cvalue.crawler.fetcher.command

import org.apache.karaf.shell.console.OsgiCommandSupport
import org.apache.karaf.shell.commands.{Argument, Command}
import scala.concurrent.{ExecutionContext, Future, Await}
import scala.concurrent.duration._
import ExecutionContext.Implicits.global
import be.cvalue.crawler.fetcher.api.UrlFetcher

@Command(scope = "fetcher", name = "fetch", description="Fetches an url and prints it to the console")
class FetchUrlCommand extends OsgiCommandSupport {

  @Argument(index = 0, name = "url", description = "The url to be fetched", required = true, multiValued = false)
  var url:String = null;

  override def doExecute() : Object = {
    val f = Option(getService(classOf[UrlFetcher]))

    val future = f.map(_.fetch(url)).getOrElse(Future{"No UrlFetcherservice found"})

    val res = Await.result(future,5 seconds)
    return res;
  }
}
