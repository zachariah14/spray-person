name := """spray-person"""

organization  := "com.example"

version       := "0.1"

scalaVersion  := "2.10.3"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8", "-feature")

resolvers ++= Seq(
  "spray repo" at "http://repo.spray.io/"
)

libraryDependencies ++= {
  val akkaV = "2.2.3"
  val sprayV = "1.2.0"
  Seq(
    "io.spray"            %   "spray-servlet"     % sprayV,
    "io.spray"            %   "spray-routing"     % sprayV,
    "io.spray"            %   "spray-testkit"     % sprayV,
    "io.spray"            %   "spray-client"      % sprayV,
    "io.spray"            %   "spray-util"        % sprayV,
    "io.spray"            %   "spray-caching"     % sprayV,
    "io.spray"            %   "spray-can"         % sprayV,
    "io.spray"            %%  "spray-json"        % "1.2.5",
    "com.typesafe.akka"   %%  "akka-slf4j"        % "2.1.4",
    "ch.qos.logback"      %   "logback-classic"   % "1.0.13",
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV,
    "org.specs2"          %%  "specs2"        % "2.2.3" % "test"
  )
}

//sbt-revolver plugin allows restarting the application when files change (including angular files in the /app folder)
//Just run sbt or activator with the command `~ re-start` instead of `run`
Revolver.settings

unmanagedResourceDirectories in Compile <+= (baseDirectory)

excludeFilter in unmanagedResources := HiddenFileFilter || "node_modules*" || "project*" || "target*"


fork in run := true