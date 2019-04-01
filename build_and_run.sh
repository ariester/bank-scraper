
#!/bin/sh

mvn clean package

mvn exec:java -D exec.mainClass=pl.astedler.bankscraper.Main