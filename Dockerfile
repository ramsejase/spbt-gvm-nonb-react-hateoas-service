# Stage 1: Build 

FROM container-registry.oracle.com/graalvm/native-image:21 AS builder

WORKDIR /build

COPY . /build

RUN ./mvnw --no-transfer-progress native:compile -Pnative

# Stage 2: Install

FROM container-registry.oracle.com/os/oraclelinux:9-slim

COPY --from=builder /build/target/spbt-gvm-nonb-react-hateoas-service app

EXPOSE 8080

ENTRYPOINT ["/app"]