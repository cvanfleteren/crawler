package be.cvalue.crawler.fetcher.web

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import be.cvalue.crawler.fetcher.api.UrlFetcher
import scala.concurrent.ExecutionContext
import javax.servlet.annotation.WebServlet
import ExecutionContext.Implicits.global

@WebServlet(asyncSupported = true, name = "fetch")
class FetcherServlet(urlFetcher:UrlFetcher) extends HttpServlet {

  override def doGet( req : HttpServletRequest, resp:HttpServletResponse ) : Unit =
  {

    val asyncContext = req.startAsync();
    val f = urlFetcher.fetch("http://www.google.be");
    f onSuccess {
        case msg => {
          resp.getWriter.write(msg)
          asyncContext.complete()
        }
    }

    f onFailure {
      case _ => {
        resp.getWriter.write("Error")
        asyncContext.complete()
      };
    }
  }

}