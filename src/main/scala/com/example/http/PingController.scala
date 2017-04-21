package com.example.http

import javax.inject.{Inject, Singleton}

import com.example.service.ExampleService
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import finatra.views.{Freemarker, FreemarkerConfigurationFactory}

@Singleton
class PingController @Inject()(service: ExampleService, freemarkerConfigurationFactory: FreemarkerConfigurationFactory) extends Controller {
  get("/ping") { _: Request =>
    info("ping")
    response.ok.plain("pong" + System.currentTimeMillis())
  }

  get("/demo") { request: Request =>
    val buffer = new StringBuffer()
    buffer.append(request.params.map { t => t._1 + ":" + t._2 }.mkString("<br/>"))
    buffer.append(request.cookies.map { t => t._1 + ":" + t._2.value }.mkString("<br/>"))

    DemoView(buffer.toString)
  }
}

@Freemarker("demo")
case class DemoView(name: String)
