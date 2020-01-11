FROM hseeberger/scala-sbt
WORKDIR /sukaku
ADD . /sukaku
CMD sbt test