package com.aquino.moneyhelper.repository;

import com.aquino.moneyhelper.models.Operacion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toshiba on 11/09/2017.
 */

public class OperacionRepository {

    private static OperacionRepository _INSTANCE = null;

    private OperacionRepository(){}

    public static OperacionRepository getInstance(){
        if(_INSTANCE == null)
            _INSTANCE = new OperacionRepository();
        return _INSTANCE;
    }

    private List<Operacion> operaciones = new ArrayList<>();

    public List<Operacion> getOperaciones() {
        return this.operaciones;
    }

    public void agregarOperacion(Operacion operacion){
        this.operaciones.add(operacion);
    }
}


