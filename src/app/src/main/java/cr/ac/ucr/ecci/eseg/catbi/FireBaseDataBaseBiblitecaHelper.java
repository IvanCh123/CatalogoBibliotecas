package cr.ac.ucr.ecci.eseg.catbi;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import cr.ac.ucr.ecci.eseg.catbi.ui.Resultado.Material;

public class FireBaseDataBaseBiblitecaHelper {
    private FirebaseDatabase database;
    private DatabaseReference referenciaBiblioteca;
    private DatabaseReference referenciaMaterial;

    private String[] filtro;
    private List<ListarBibliotecas> listaBibliotecas= new ArrayList<>();
    private List<Material> listaMaterial = new ArrayList<>();

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

    public FireBaseDataBaseBiblitecaHelper() {
        database=FirebaseDatabase.getInstance();
        referenciaBiblioteca= database.getReference("Bibliotecas");
        referenciaMaterial= database.getReference("Material");
        filtro = null;
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

                    realizarFiltrado(material, filtro, listaMaterial);
                }
                materialDataStatus.DataIsLoaded(listaMaterial,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    private void realizarFiltrado(Material material, String[] filtro, List<Material> listaMaterial) {
        String titulo = StringUtils.stripAccents(material.getTitulo()).toLowerCase();
        String autor = StringUtils.stripAccents(material.getAutor()).toLowerCase();
        String idioma = StringUtils.stripAccents(material.getIdioma()).toLowerCase();

        switch (filtro[1]){
            case "titulo":
                if(titulo.contains(filtro[0])){
                    listaMaterial.add(material);
                }
                break;
            case "autor":
                if(autor.contains(filtro[0])){
                    listaMaterial.add(material);
                }
                break;
            case "idioma":
                if(idioma.contains(filtro[0])){
                    listaMaterial.add(material);
                }
                break;
            case "todo":
                if(titulo.contains(filtro[0]) || autor.contains(filtro[0]) || idioma.contains(filtro[0])){
                    listaMaterial.add(material);
                }
                break;
        }
    }
}
