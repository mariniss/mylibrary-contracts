package com.fm.mylibrary.producer.pact

import com.fm.mylibrary.producer.app.MyLibraryAppServer
import org.scalatest.{BeforeAndAfterAll, FunSpec, Matchers}
import com.itv.scalapact.ScalaPactVerify._


class MyLibraryServerPactSpec extends FunSpec with Matchers with BeforeAndAfterAll {

  override def beforeAll() {
    MyLibraryAppServer.main(Array())
  }

  override def afterAll() {
    MyLibraryAppServer.stopApplication()
  }


  describe("Verifying MyLibrary server") {
    it("should be able to respect the contract"){
      verifyPact
        .withPactSource(loadFromLocal("target/pacts"))
        .noSetupRequired // We did the setup in the beforeAll() function thanks to mockData
        .runVerificationAgainst("localhost", 9999)
    }
  }
}
