//Needs Hypervisor on Windows(aka Professional versions and up)
FROM hseeberger/scala-sbt
WORKDIR /sukaku
ADD . /sukaku
CMD sbt test