package memorial.luiz.api.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@Slf4j
public class StorageService {

    private final static Path root = Paths.get("src/main/resources/upload");

    public StorageService() {
        try {
            Files.createDirectories(root);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar diretório de uploads.", e);
        }
    }

    public String salvar(MultipartFile file) {
        try {
            String nome = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path destino = root.resolve(nome);

            Files.copy(file.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);

            return nome;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar arquivo.", e);
        }
    }
}
