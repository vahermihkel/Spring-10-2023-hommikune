package ee.mihkel.lemmikloomad.service;

import ee.mihkel.lemmikloomad.entity.Lemmikloom;
import ee.mihkel.lemmikloomad.entity.Omanik;
import ee.mihkel.lemmikloomad.repository.LemmikloomRepository;
import ee.mihkel.lemmikloomad.repository.OmanikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LemmikloomService {

    // 9.10

    @Autowired
    LemmikloomRepository lemmikloomRepository;

    @Autowired
    OmanikRepository omanikRepository;

    public void lisaLoom(String nimetus, double kaal, String omanikuNimi) {
        Lemmikloom lemmikloom = Lemmikloom.builder()
                .nimetus(nimetus)
                .kaal(kaal)
                .build();
        lemmikloomRepository.save(lemmikloom);

        Optional<Omanik> omanikOptional = omanikRepository.findById(omanikuNimi);
        Omanik omanik = omanikOptional.orElseGet(() -> new Omanik(omanikuNimi, new ArrayList<>()));

        List<Lemmikloom> omanikuLemmikloomad = omanik.getLemmikloomad();
        omanikuLemmikloomad.add(lemmikloom);
        omanik.setLemmikloomad(omanikuLemmikloomad);

        // pean muutma teda andmebaasis
        omanikRepository.save(omanik); // nii muutmine kui salvestamine .save() kaudu
    }
}
