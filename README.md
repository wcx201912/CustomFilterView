# CustomFilterView

实现页面的筛选功能
使用方法
先在 build.gradle(Project:XXXX) 的 repositories 添加 maven { url 'https://jitpack.io' } 一定要加上这个，否则会提示依赖失败

```java
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```


然后在 build.gradle(Module:app) 的 dependencies 添加:implementation 'com.github.wcx201912:CustomFilterView:Tag'

```java
  dependencies {
   compile fileTree(dir: 'libs', include: ['*.jar'])
   implementation 'androidx.appcompat:appcompat:1.0.0'

   /*添加依赖*/
   implementation 'com.github.wcx201912:CustomFilterView:v1.0.0'
}
```





