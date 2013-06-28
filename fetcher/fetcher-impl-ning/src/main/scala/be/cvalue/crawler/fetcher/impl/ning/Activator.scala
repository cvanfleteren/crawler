package be.cvalue.crawler.fetcher.impl.ning

import org.osgi.framework.{BundleContext, BundleActivator}

class Activator extends BundleActivator{

  def start(context: BundleContext) {
    println("Started "+context.getBundle.getSymbolicName+":"+context.getBundle().getVersion)
  }

  def stop(context: BundleContext) {
    println("Stopped "+context.getBundle.getSymbolicName+":"+context.getBundle().getVersion)
  }

}
