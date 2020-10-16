package cr.ac.ucr.ecci.eseg.catbi;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import cr.ac.ucr.ecci.eseg.catbi.ui.Perfil.Reservacion;
import cr.ac.ucr.ecci.eseg.catbi.ui.Perfil.Usuarios;
import cr.ac.ucr.ecci.eseg.catbi.ui.Resultado.Material;

public class FireBaseDataBaseBiblitecaHelper {
    private FirebaseDatabase database;
    private DatabaseReference referenciaBiblioteca;
    private DatabaseReference referenciaMaterial;
    private DatabaseReference referenciaUsuarios;
    private DatabaseReference referenciaReserva;

    private String filtro;
    private List<ListarBibliotecas> listaBibliotecas= new ArrayList<>();
    private List<Material> listaMaterial = new ArrayList<>();
    private Usuarios usuario;
    private List<Reservacion> listaReservaciones = new ArrayList<>();
    public List<Material> getListaMaterial() {
        return listaMaterial;
    }

    public interface DataStatus{
        void dataLoaded(List<ListarBibliotecas>ListaBibliotecas,List<String>keys);
        void dataInserted();
        void dataDeleted();
        void dataUpdated();
    }

    public interface MaterialDataStatus{
        void DataIsLoaded(List<Material> material, List<String> keys);
    }

    public interface UsuariosDataStatus{
        void DataIsLoaded(Usuarios usuario);
    }

    public interface ReservaDataStatus{
        void DataIsLoaded(List<Reservacion> reservacion, List<String> keys);
    }

    public FireBaseDataBaseBiblitecaHelper() {
        database=FirebaseDatabase.getInstance();
        referenciaBiblioteca= database.getReference("Bibliotecas");
        referenciaMaterial= database.getReference("Material");
        referenciaUsuarios= database.getReference("Usuarios");
        referenciaReserva = database.getReference("Usuario_Material");
        filtro = "";
    }

    public void readBibliotecas(final DataStatus dataStatus){
        referenciaBiblioteca.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaBibliotecas.clear();
                List<String>keys= new ArrayList<>();
                for(DataSnapshot keyNode: dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    ListarBibliotecas listaBiblioteca= keyNode.getValue(ListarBibliotecas.class);
                    listaBibliotecas.add(listaBiblioteca);
                }
                dataStatus.dataLoaded(listaBibliotecas,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void readMaterial(final MaterialDataStatus materialDataStatus, final String filtro){
        this.filtro = filtro;
        referenciaMaterial.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaMaterial.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Material material = keyNode.getValue(Material.class);
                    material.setID(keyNode.getKey());

                    if(material.getTitulo().toLowerCase().contains(filtro.toLowerCase())){
                        listaMaterial.add(material);
                    }
                }
                materialDataStatus.DataIsLoaded(listaMaterial,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    public void readUsuarios(final UsuariosDataStatus userDataStatus, final String correoP){
        referenciaUsuarios.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    if(ds.child("correo").getValue().equals(correoP)){
                        String nombreUsuarioFromDB = ds.child("nombre").getValue(String.class);
                        String correoFromDB = ds.child("correo").getValue(String.class);
                        usuario = new Usuarios(correoFromDB, nombreUsuarioFromDB);
                        userDataStatus.DataIsLoaded(usuario);
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void readReservas(final ReservaDataStatus reservaDataStatus, final String correoP){
        referenciaReserva.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaReservaciones.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    if(ds.child("correoUsuario").getValue().equals(correoP)){
                        //String tituloMaterialFromDB = ds.child("tituloMaterial").getValue(String.class);
                        //String fechaLimiteFromDB = ds.child("fechaLimite").getValue(String.class);
                        //Reservacion reserva = new Reservacion(tituloMaterialFromDB,fechaLimiteFromDB);
                        keys.add(ds.getKey());
                        Reservacion reserva = ds.getValue(Reservacion.class);
                        listaReservaciones.add(reserva);
                    }
                }
                reservaDataStatus.DataIsLoaded(listaReservaciones, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
