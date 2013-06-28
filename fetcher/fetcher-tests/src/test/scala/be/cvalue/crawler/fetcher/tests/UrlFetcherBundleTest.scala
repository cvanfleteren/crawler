package be.cvalue.fetcher.tests

import org.junit.Assert._
import org.ops4j.pax.exam.CoreOptions._

import org.apache.karaf.tooling.exam.options.KarafDistributionOption.karafDistributionConfiguration

import javax.inject.Inject

import org.junit.{Test}
import org.junit.runner.RunWith
import org.ops4j.pax.exam.junit.{ExamReactorStrategy, JUnit4TestRunner, Configuration}

import org.ops4j.pax.exam.Option
import org.osgi.framework.BundleContext
import org.ops4j.pax.exam.spi.reactors.AllConfinedStagedReactorFactory
import be.cvalue.crawler.fetcher.api.UrlFetcher

@RunWith(classOf[JUnit4TestRunner])
@ExamReactorStrategy(Array(classOf[AllConfinedStagedReactorFactory]))
class UrlFetcherBundleTest {

  @Inject
  var context:BundleContext = null

  @Configuration
  def config : Array[Option] =  {

    return options(
      karafDistributionConfiguration().frameworkUrl(maven().groupId("org.apache.karaf").artifactId("apache-karaf").`type`("zip").versionAsInProject()).karafVersion("2.3.1").name("Apache Karaf"),
      provision(
        mavenBundle("be.cvalue.crawler.fetcher", "fetcher-api").versionAsInProject(),
        mavenBundle("be.cvalue.crawler.fetcher", "fetcher-impl-ning").versionAsInProject(),
        mavenBundle("org.scala-lang", "scala-library").versionAsInProject(),
        mavenBundle("com.ning", "async-http-client").versionAsInProject()
      )
      ,
      //debugConfiguration(),
      junitBundles()
    )
  }

  @Test
  def test()  {
    Thread.sleep(1000)//sleep needed for making "sure" the bundles get inited :/
    assertNotNull(context)
    val ref = context.getServiceReference(classOf[UrlFetcher].getName)
    val service = context.getService(ref)
    assertNotNull(service)
  }

}
