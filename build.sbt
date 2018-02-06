name := "myLibrary-contracts"

version := "0.1"

scalaVersion := "2.12.4"

enablePlugins(ScalaPactPlugin)


libraryDependencies ++= Seq(
  "com.itv"           %% "scalapact-argonaut-6-2"  % "2.2.0" % "test",
  "com.itv"           %% "scalapact-http4s-0-16-2" % "2.2.0" % "test",
  "com.itv"           %% "scalapact-scalatest"     % "2.2.0" % "test",
  "org.scalatest"     %% "scalatest"               % "3.0.1" % "test",
  "org.json4s"        %% "json4s-native"           % "3.5.0",
  "com.typesafe.akka" %% "akka-http"               % "10.0.11",
  "com.typesafe.akka" %% "akka-http-spray-json"    % "10.0.11"
)