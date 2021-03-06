package com.example.http

import java.io._
import javax.inject.{Inject, Singleton}

import com.example.service.ExampleService
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.finatra.http.request.RequestUtils
import finatra.freemarker.{Freemarker, FreemarkerConfigurationFactory}

@Singleton
class HomeController @Inject()(service: ExampleService, freemarkerConfigurationFactory: FreemarkerConfigurationFactory) extends Controller {
  get("/ping") { _: Request =>
    info("ping")
    response.ok.plain("pong" + System.currentTimeMillis())
  }

  get("/demo") { _: Request =>
    DemoView("YaFengLi", List(Person("test_1", 1), Person("test_2", 2), Person("test_3", 3)))
  }
  get("/") { request: Request =>
    response.ok.html(
      s"""<h1>Hello ${request.ctx}!</h1>
         |params:${request.params.map { t => t._1 + ":" + t._2 }.mkString("<br/>")}
         |<br/>cookies:${request.cookies.map { t => t._1 + ":" + t._2.value }.mkString("<br/>")}
         |<br/>Freemarker:<a href="/demo"> Freemarker Demo Page!</a>
       """.stripMargin)
  }

  post("/upload") { request: Request =>
    RequestUtils.multiParams(request).filter(_._1 == "file").headOption match {
      case Some(k) =>
        k._2.filename match {
          case Some(fn) =>
            val file = new File(fn)
            val out = new BufferedOutputStream(new FileOutputStream(file))
            try out.write(k._2.data)
            finally {
              out.flush()
              out.close()
            }
            response.ok.header("content-disposition",s"""inline; filename="${fn}.txt"""").file(file)
          case None => response.notFound
        }
      case None => response.notFound
    }
  }
}

@Freemarker("demo")
case class DemoView(name: String, persons: List[Person])

case class Person(name: String, age: Int)
