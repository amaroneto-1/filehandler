# Typeprocessor

Typeprocessor é uma aplicação que consome um tópico do kafka, onde as linhas de diversos arquivos que foram escritas com o app #Filehandler, classifica-os, realiza um processamento, e finalmente realiza a escrita dos resultados em um tópico próprio . 

## Requirements

- Java 11
- Apache Kafka + Zookeeper

## Installation

Basta clonar o repositório no GIT HUB

```bash
git clone https://github.com/amaroneto-1/typeprocessor.git
```

## Usage
Basta usar o gradle wrapper executando o comando:

```linux
./gradlew bootRun
```
E pronto a aplicação já estará consumindo o que for produzido em #Filehandler