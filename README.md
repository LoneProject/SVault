# 플러그인 공지
플러그인 API로 사용하실 때 반드시 `plugin.yml`에 아래 텍스트를 추가해주세요.
```yaml
depend:
  - SVault
```

<br>

# 플러그인 소개
해당 플러그인은 VaultAPI와 같은 경제와 관련된 API 플러그인입니다.<br>
해당 플러그인은 현재 Skript에서는 사용할 수 없지만, 추후 추가할 예정입니다.<br>
플러그인 API는 맨 밑에 자세히 나와있습니다.

<br>

# 플러그인 저작권
해당 플러그인의 저작권은 `lone64`에게 있습니다.<br>
플러그인 소스코드를 사용하고자 한다면, 출처 또는 개발자 이름을 남겨주세요!

<br>

# 플러그인 버전
해당 플러그인이 호환하는 버전은 `1.12 ~ 1.20.1`입니다.

<br>

# 플러그인 설정파일
`해당 플러그인의 설정파일은 존재하지 않습니다.`

<br>

# 플러그인 API
Use API In Gradle
```groovy
repositories {
    maven { url "https://nexus.lone64.org:8081/repository/maven-public/" }
}

dependencies {
    compileOnly "org.lone64:api-vault:1.0.2a2"
}
```

Use API In Maven
```xml
<repositories>
    <repository>
        <id>LoneProject</id>
        <url>https://nexus.lone64.org:8081/repository/maven-public/</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>org.lone64</groupId>
        <artifactId>api-vault</artifactId>
        <version>1.0.2a2</version>
    </dependency>
</dependencies>
```

VaultUtil.java
```java
public class VaultUtil {

    private static final Economy economy = new AbstractEconomy();

    // 플레이어의 소지금을 설정하는 코드
    public static boolean set(OfflinePlayer player, double amount) {
        EconomyResponse response = economy.setAmount(player, amount);
        return response.getType().equals(EconomyResponse.ResponseType.SUCCESS);
    }

    // 플레이어의 소지금에 추가하는 코드
    public static boolean add(OfflinePlayer player, double amount) {
        EconomyResponse response = economy.addAmount(player, amount);
        return response.getType().equals(EconomyResponse.ResponseType.SUCCESS);
    }

    // 플레이어의 소지금에서 차감하는 코드
    public static boolean subtract(OfflinePlayer player, double amount) {
        EconomyResponse response = economy.subAmount(player, amount);
        return response.getType().equals(EconomyResponse.ResponseType.SUCCESS);
    }

    // 플레이어의 소지금을 가져오는 코드
    public static double get(OfflinePlayer player) {
        return economy.getAmount(player);
    }

}
```

<br>

# 스크립트 API
```skript
set player's money to 1000
add 1000 to player's money
subtract 1000 from player's money

set money of player to 1000
add 1000 to money of player
subtract 1000 from money of player
```
