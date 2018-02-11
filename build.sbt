name := "myLibrary-contracts"

version := "0.1"

scalaVersion := "2.12.4"

enablePlugins(ScalaPactPlugin)


libraryDependencies ++= Seq(

  //Common dependencies
  "com.typesafe.akka"  %% "akka-stream"             % "2.5.9",
  "com.typesafe.akka"  %% "akka-http"               % "10.0.11",
  "org.scalatest"      %% "scalatest"               % "3.0.1"   % "test",
  "org.scalamock"      %% "scalamock"               % "4.0.0"   % "test",
  "com.typesafe.akka"  %% "akka-stream-testkit"     % "2.5.9"   % "test",
  "com.typesafe.akka"  %% "akka-testkit"            % "2.5.9"   % "test",
  "com.typesafe.akka"  %% "akka-http-testkit"       % "10.0.11" % "test",
  "com.itv"            %% "scalapact-argonaut-6-2"  % "2.2.0"   % "test",
  "com.itv"            %% "scalapact-scalatest"     % "2.2.0"   % "test",
  "com.itv"            %% "scalapact-http4s-0-16-2" % "2.2.0"   % "test",

  //Consumer dependencies
  "com.typesafe.akka"  %% "akka-http-spray-json"    % "10.0.11",
  "org.slf4j"           % "slf4j-simple"            % "1.7.25",

  //Producer dependencies
  "com.typesafe.slick" %% "slick"                   % "3.2.1",
  "com.typesafe.slick" %% "slick-hikaricp"          % "3.2.1",
  "com.h2database"      % "h2"                      % "1.4.196",
  "org.flywaydb"        % "flyway-core"             % "5.0.7",
  "io.circe"           %% "circe-core"              % "0.9.1",
  "io.circe"           %% "circe-generic"           % "0.9.1",
  "io.circe"           %% "circe-parser"            % "0.9.1"
)