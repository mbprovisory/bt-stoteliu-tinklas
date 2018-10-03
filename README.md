# bt-stoteliu-tinklas

## Projekto Integracija á karkasà 
lt.baltictalents.stoteliutinklas.interfeisai package'e rasite interfeisus, kuriø implementacijas turësite sukurti grupinio projekto metu.

Sukûræ interfeiso implementacijà, atnaujinkite ir klasæ `lt.baltictalents.stoteliutinklas.implementacija.Factory`

Pavyzdþiui.:

Turime interfeisà `SampleService`:

```java
public interface SampleService {
	public String getHello();
}
```

Kai sukursite interfeiso implementacijà, pavyzdþiui su pavadinimu `SampleServiceImpl`, tada `Factory` klasæ atnaujinkite ðtai taip:

```java
public SampleService getSampleService() {
		return new SampleServiceImpl();
	}
```

`getSampleService` metodas jau yra sukurtas `Factory` klasëje, jums tik reikià já uþpildyti.
Ið tikrøjø, galite dëti koká norite kodà á metodà `getSampleService`, svarbu tik kad jis gràþintø objektà, kuris iðpildo norimà interfeisà (ðiuo atveju - `SampleService`)

## Duomenys
`lt.baltictalents.stoteliutinklas.data.hardcode.HardCodedDb` klasë turi stoteliø sàraðà, su kuriuo reiks dirbti. Importuokite ðià klasæ á savo sukurtas klases ir naudokite duomenis.

## Testavimas per Postman
Dar neapraðyta..