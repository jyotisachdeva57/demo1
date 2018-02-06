
name := "knolkart"

version := "0.1"

scalaVersion := "2.12.4"

lazy val root = (project in file("."))
  .aggregate(account, inventory, checkout, api, dashboard)


lazy val account = (project in file("accountService"))
  .settings(
    libraryDependencies ++= Seq("org.scalatest" % "scalatest_2.12" % "3.0.4" % "test",
      "log4j" % "log4j" % "1.2.17",
      "org.scalatest" % "scalatest_2.12" % "3.0.4" % "test"
    )
  )


lazy val inventory = (project in file(" inventoryService"))
  .settings(
    libraryDependencies ++= Seq("org.scalatest" % "scalatest_2.12" % "3.0.4" % "test",
      "log4j" % "log4j" % "1.2.17"
    )
  )

lazy val checkout = (project in file("checkoutService"))
  .dependsOn(account,inventory).settings(
    libraryDependencies ++= Seq("org.scalatest" % "scalatest_2.12" % "3.0.4" % "test",
      "log4j" % "log4j" % "1.2.17",
      "org.scalatest" % "scalatest_2.12" % "3.0.4" % "test"
    )

  )

lazy val api = (project in file("apiService"))
  .dependsOn(account,inventory)
  .settings(
    libraryDependencies ++= Seq("org.scalatest" % "scalatest_2.12" % "3.0.4" % "test",
      "log4j" % "log4j" % "1.2.17",
      "org.scalatest" % "scalatest_2.12" % "3.0.4" % "test"
    )
  )


lazy val dashboard = (project in file("dashboardService"))
  .settings(
    libraryDependencies ++= Seq("org.scalatest" % "scalatest_2.12" % "3.0.4" % "test",
      "log4j" % "log4j" % "1.2.17",
      "org.scalatest" % "scalatest_2.12" % "3.0.4" % "test"
    )
  )

