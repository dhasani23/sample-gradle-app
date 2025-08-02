# Enterprise Java 8 to 17 Transformation Test Application

This synthetic application simulates the complexity found in Coupang's enterprise Java applications to test Amazon Q Code Transformation (QCT) capabilities.

## Key Transformation Challenges

### 1. Java 8 Specific Features
- **CustomClassLoader**: Uses deprecated `defineClass` methods and `Unsafe` API
- **SecurityManager**: Deprecated security manager implementation
- **Finalize Methods**: Multiple classes using deprecated `finalize()`

### 2. Dependency Issues
- **Internal Dependencies**: Mock internal repositories and custom libraries
- **Forced Versions**: Spring Core 5.2.0 with forced resolution strategy
- **JAXB**: Java EE APIs removed in Java 11+
- **javax.annotation**: Annotations moved to different packages

### 3. Build System Complexity
- **Gradle 4.9**: Old Gradle version with Java 8 compatibility
- **Custom Plugins**: Enterprise-specific build logic in `buildSrc`
- **Heap Constraints**: Limited memory settings typical in enterprise CI/CD
- **Wrapper Scripts**: Custom build system simulation (Vitamin)

### 4. Testing Challenges
- **PowerMock**: Complex mocking framework with static method mocking
- **JUnit 4**: Legacy testing framework
- **Security Tests**: Tests that rely on SecurityManager

## Build Instructions

```bash
# Using enterprise build wrapper
chmod +x custom-scripts/vitamin-build.sh
./custom-scripts/vitamin-build.sh build

# Or standard Gradle
./gradlew build
```

## Expected Transformation Issues

1. **Unsafe API Usage**: `sun.misc.Unsafe` access will fail
2. **SecurityManager**: Deprecated and removed in Java 17
3. **JAXB Dependencies**: Need explicit dependencies for Java 11+
4. **javax.annotation**: Package moved to `jakarta.annotation`
5. **ClassLoader Methods**: Deprecated `defineClass` overloads
6. **Build Tool Compatibility**: Gradle 4.9 may need updates
7. **Memory Settings**: `-XX:MaxPermSize` removed in Java 8+

This application provides a comprehensive test case for enterprise Java transformation scenarios.