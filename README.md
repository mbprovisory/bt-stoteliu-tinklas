# bt-stoteliu-tinklas

## Projekto Integracija į karkasą
lt.baltictalents.stoteliutinklas.interfeisai package'e rasite interfeisus, kurių implementacijas turėsite sukurti grupinio projekto metu.

Sukūrę interfeiso implementaciją, atnaujinkite ir klasę `lt.baltictalents.stoteliutinklas.implementacija.Factory`

Pavyzdžiui.:

Turime interfeisą `SampleService`:

```java
public interface SampleService {
	public String getHello();
}
```

Kai sukursite interfeiso implementaciją, pavyzdžiui su pavadinimu `SampleServiceImpl`, tada `Factory` klasę atnaujinkite štai taip:

```java
public SampleService getSampleService() {
		return new SampleServiceImpl();
	}
```

`getSampleService` metodas jau yra sukurtas `Factory` klasėje, jums tik reikia jį užpildyti.
Iš tikrųjų, galite dėti kokį norite kodą į metodą `getSampleService`, svarbu tik kad jis grąžintų objektą, kuris išpildo norimą interfeisą (šiuo atveju - `SampleService`)

## Duomenys
`lt.baltictalents.stoteliutinklas.data.hardcode.HardCodedDb` klasė turi stotelių sąrašą, su kuriuo reikės dirbti. Importuokite šią klasę į savo sukurtas klases ir naudokite duomenis.

## Testavimas per Postman
Dar neaprašyta..