package com.notas.primerProyecto.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.notas.primerProyecto.entity.Nota;
import com.notas.primerProyecto.model.MNota;

@Component( "ConvertidorNota" )
public class ConvertidorNota {

  public List<MNota> convertirLista( List<Nota> notas ) {
    List<MNota> mNotas = new ArrayList<>();
    for ( Nota nota : notas ) {
      mNotas.add( new MNota( nota ) );
    }
    return mNotas;
  }

}
