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

# 플러그인 API
Use API In Gradle
```groovy
repositories {
    maven { url "https://repo.loneproject.org/repository/maven-public/" }
}

dependencies {
    compileOnly "org.lone64:Vault:1.0.0-beta1"
}
```

Use API In Maven
```xml
<repositories>
    <repository>
        <id>LoneProject</id>
        <url>https://repo.loneproject.org/repository/maven-public/</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>org.lone64</groupId>
        <artifactId>Vault</artifactId>
        <version>1.0.0-beta1</version>
    </dependency>
</dependencies>
```

```java
public class VaultUtil {

    private final Economy economy;

    public VaultUtil() {
        this.economy = new AbstractEconomy();
    }

    public boolean set(OfflinePlayer player, double amount) {
        EconomyResponse response = this.economy.setAmount(player, amount);
        return response.getType().equals(EconomyResponse.ResponseType.SUCCESS);
    }

    public boolean add(OfflinePlayer player, double amount) {
        EconomyResponse response = this.economy.addAmount(player, amount);
        return response.getType().equals(EconomyResponse.ResponseType.SUCCESS);
    }

    public boolean subtract(OfflinePlayer player, double amount) {
        EconomyResponse response = this.economy.subAmount(player, amount);
        return response.getType().equals(EconomyResponse.ResponseType.SUCCESS);
    }

    public double get(OfflinePlayer player) {
        return this.economy.getAmount(player);
    }

}
```

# 스크립트 API
```skript
set player's money to 1000
add 1000 to player's money
subtract 1000 from player's money

set money of player to 1000
add 1000 to money of player
subtract 1000 from money of player
```
