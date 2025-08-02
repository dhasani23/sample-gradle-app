#!/bin/bash
# Simulating Coupang's Vitamin build system wrapper

set -e

JAVA_HOME=${JAVA_HOME:-/usr/lib/jvm/java-8-openjdk}

# Detect Java version and set appropriate options
JAVA_VERSION=$($JAVA_HOME/bin/java -version 2>&1 | head -n 1 | cut -d'"' -f2)
if [[ $JAVA_VERSION == 1.8* ]]; then
    GRADLE_OPTS="-Xmx512m -XX:MaxPermSize=256m -Dfile.encoding=UTF-8"
else
    GRADLE_OPTS="-Xmx512m -Dfile.encoding=UTF-8"
fi

echo "=== Enterprise Build System (Vitamin Simulation) ==="
echo "Java Home: $JAVA_HOME"
echo "Gradle Options: $GRADLE_OPTS"

echo "Java Version: $JAVA_VERSION"

if [[ ! $JAVA_VERSION == 1.8* ]]; then
    echo "WARNING: Expected Java 8, found $JAVA_VERSION"
    echo "Note: Adjusted JVM options for compatibility"
fi

# Set enterprise-specific environment
export ENTERPRISE_ENV=production
export INTERNAL_REPO_URL=http://internal-repo.company.com
export SECURITY_POLICY=strict

# Run Gradle with enterprise settings
export GRADLE_OPTS
./gradlew "$@" \
    -Prepo.user=${REPO_USER:-default} \
    -Prepo.password=${REPO_PASSWORD:-default} \
    -Penterprise.mode=true \
    --no-daemon \
    --stacktrace