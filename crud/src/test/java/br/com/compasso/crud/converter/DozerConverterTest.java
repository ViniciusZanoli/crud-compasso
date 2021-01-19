package br.com.compasso.crud.converter;

import br.com.compasso.crud.converter.mocks.MockClients;
import br.com.compasso.crud.model.Cliente;
import br.com.compasso.crud.model.dto.ClienteDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class DozerConverterTest {

  MockClients inputObject;

  @Before
  public void setUp(){
    inputObject = new MockClients();
  }

  @Test
  public void parseEntityToDtoTest(){
    ClienteDto output = DozerConverter.parseObject(inputObject.mockEntity(), ClienteDto.class);
    Assert.assertEquals(Long.valueOf(0L), output.getKey());
    Assert.assertEquals("Teste0", output.getNomeCompleto());
    Assert.assertEquals(output.getSexo(), output.getSexo());
    Assert.assertEquals(output.getDataDeNacimento(), output.getDataDeNacimento());
    Assert.assertEquals(20, output.getIdade());
  }

  @Test
  public void parseEntityListToDtoList(){
    List<ClienteDto> outputList = DozerConverter.parseListObjects(inputObject.mockEntityList(), ClienteDto.class);
    ClienteDto outputZero = outputList.get(0);

    Assert.assertEquals(Long.valueOf(0L), outputZero.getKey());
    Assert.assertEquals("Teste0", outputZero.getNomeCompleto());
    Assert.assertEquals(outputZero.getSexo(), outputZero.getSexo());
    Assert.assertEquals(outputZero.getDataDeNacimento(), outputZero.getDataDeNacimento());
    Assert.assertEquals(20, outputZero.getIdade());
    Assert.assertEquals(20, outputZero.getIdade());
  }

}
