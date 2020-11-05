package cr.ac.ucr.ecci.eseg.catbi.BaseDatos;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cr.ac.ucr.ecci.eseg.catbi.ui.Bibliotecas.ListarBibliotecas;
import cr.ac.ucr.ecci.eseg.catbi.ui.Reserva.ReservaMaterial;
import cr.ac.ucr.ecci.eseg.catbi.ui.Perfil.Reservacion;
import cr.ac.ucr.ecci.eseg.catbi.ui.Perfil.Usuarios;
import cr.ac.ucr.ecci.eseg.catbi.ui.Resultado.Material;

public class FireBaseDataBaseBiblitecaHelper {
    private FirebaseDatabase database;
    private DatabaseReference referenciaBiblioteca;
    private DatabaseReference referenciaMaterial;
    private DatabaseReference referenciaUsuarios;
    private DatabaseReference referenciaReserva;
    private DatabaseReference referenciaReservacion;
    private FirebaseAuth mAuth;
    FirebaseUser user;


    private String[] filtro;
    private List<ListarBibliotecas> listaBibliotecas= new ArrayList<>();
    private List<ReservaMaterial> listaReserva=new ArrayList<>();
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
        referenciaReservacion=database.getReference("Usuario_Material");
        //filtro = "";
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        filtro = null;
        referenciaUsuarios= database.getReference("Usuarios");
        referenciaReserva = database.getReference("Usuario_Material");
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

    public void readMaterial(final MaterialDataStatus materialDataStatus, final String[] filtro){
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

                    if(filtro[2].equalsIgnoreCase("todas")){
                        realizarFiltradoSinColeccion(material, filtro, listaMaterial);
                    }else{
                        realizarFiltradoConColeccion(material, filtro, listaMaterial);
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

    private void realizarFiltradoConColeccion(Material material, String[] filtro, List<Material> listaMaterial) {
        String titulo = StringUtils.stripAccents(material.getTitulo()).toLowerCase();
        String autor = StringUtils.stripAccents(material.getAutor()).toLowerCase();
        String idioma = StringUtils.stripAccents(material.getIdioma()).toLowerCase();

        String palabraClave = filtro[0];
        String campoBusqueda = filtro[1];

        String colecionFiltro = filtro[2];
        String colecionRecuperada = StringUtils.stripAccents(material.getColeccion()).toLowerCase();

        switch (campoBusqueda){
            case "titulo":
                if(titulo.contains(palabraClave) && colecionFiltro.equalsIgnoreCase(colecionRecuperada)){
                    listaMaterial.add(material);
                }
                break;
            case "autor":
                if(autor.contains(palabraClave) && colecionFiltro.equalsIgnoreCase(colecionRecuperada)){
                    listaMaterial.add(material);
                }
                break;
            case "idioma":
                if(idioma.contains(palabraClave) && colecionFiltro.equalsIgnoreCase(colecionRecuperada)){
                    listaMaterial.add(material);
                }
                break;
            case "todo":
                if((titulo.contains(palabraClave) || autor.contains(palabraClave) || idioma.contains(palabraClave)) && colecionFiltro.equalsIgnoreCase(colecionRecuperada) ){
                    listaMaterial.add(material);
                }
                break;
        }
    }

    private void realizarFiltradoSinColeccion(Material material, String[] filtro, List<Material> listaMaterial) {
        String titulo = StringUtils.stripAccents(material.getTitulo()).toLowerCase();
        String autor = StringUtils.stripAccents(material.getAutor()).toLowerCase();
        String idioma = StringUtils.stripAccents(material.getIdioma()).toLowerCase();

        String palabraClave = filtro[0];
        String campoBusqueda = filtro[1];

        switch (campoBusqueda){
            case "titulo":
                if(titulo.contains(palabraClave)){
                    listaMaterial.add(material);
                }
                break;
            case "autor":
                if(autor.contains(palabraClave)){
                    listaMaterial.add(material);
                }
                break;
            case "idioma":
                if(idioma.contains(palabraClave)){
                    listaMaterial.add(material);
                }
                break;
            case "todo":
                if(titulo.contains(palabraClave) || autor.contains(palabraClave) || idioma.contains(palabraClave)){
                    listaMaterial.add(material);
                }
                break;
        }
    }

    public boolean addReserva(ReservaMaterial r,String c ){
        actualizaCantMaterial(c,r.getMaterialID());
        DateFormat df = new SimpleDateFormat("yyMMddHHmmssZ");
        String date = df.format(Calendar.getInstance().getTime());

        try{
            r.setCorreoUsuario(user.getEmail());
        }catch (Exception e){
            r.setCorreoUsuario("DESCONOCIDO");
        }

        int dias= Integer.parseInt(r.getFechaLimite());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,dias);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String fechaLimite = sdf.format(calendar.getTime());
        r.setFechaLimite(fechaLimite);
        boolean resultado;
        try{
            referenciaReservacion.child(String.valueOf(date)).setValue(r);
            resultado=true;
        }catch (Exception e){
            resultado=false;
        }
        return resultado;

    }

    private void actualizaCantMaterial(String  cant, String id){
        int c=Integer.parseInt(cant)-1;
        referenciaMaterial.child(id).child("cantidad").setValue(String.valueOf(c));
    }

    // funcion que agrega un nuevo valor a la base de datos y genera una id unica
    public boolean addMaterial( Material m){
        //contarHijosMaterial();
        boolean add=true;
        int inicio=0;
        int fin=2;
        DateFormat df = new SimpleDateFormat("yyMMddHHmmssZ");
        String date = df.format(Calendar.getInstance().getTime());
        String titulo=m.getTitulo();
        String year=m.getAño();
        String biblio=m.getBiblioteca();
        String idM=year+date+biblio.substring(inicio,fin)+titulo.substring(inicio,1);
        try{
            referenciaMaterial.child(idM).setValue(m);
        }catch (Exception e){
            add=false;
        }
        return add;
    }

    public void actualizarDatos(Material material){
        referenciaMaterial.child(material.getID()).child("autor").setValue(material.getAutor());
        referenciaMaterial.child(material.getID()).child("biblioteca").setValue(material.getBiblioteca());
        referenciaMaterial.child(material.getID()).child("coleccion").setValue(material.getColeccion());
        referenciaMaterial.child(material.getID()).child("cantidad").setValue(material.getCantidad());
        referenciaMaterial.child(material.getID()).child("formato").setValue(material.getFormato());
        referenciaMaterial.child(material.getID()).child("idioma").setValue(material.getIdioma());
        referenciaMaterial.child(material.getID()).child("titulo").setValue(material.getTitulo());
    }

    public boolean eliminarMaterial(String id) {
        try{
            referenciaMaterial.child(id).removeValue();
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
