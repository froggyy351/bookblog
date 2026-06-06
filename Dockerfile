# ベースイメージの指定
FROM eclipse-temurin:21-jdk-alpine

# コンテナ内の作業ディレクトリを指定
WORKDIR /app

# ビルド済みのjarをコンテナ内にコピー
COPY target/bookblog-0.0.1-SNAPSHOT.jar app.jar

# jar起動コマンド
ENTRYPOINT ["java", "-jar", "app.jar"]
