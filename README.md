# spbt-gvm-nonb-react-hateoas-service
SpringBoot + HATEOAS + GraalVM + Native + Docker + Non-Blocking
At this time of implementaiton, the Spring hateoas is not fully matured to use Flux. The links are still mono and blocking.
At the same time, the return type is also a Mono type and fully blocking.
Therefore, its no effective to use Reactive in Hateoas. Hopefully in future, this should change.
Usage of Record should enhance the performance to the object mapper transformation to the JSON-API representation. 
