package br.com.aula.empresa.services;

import br.com.aula.empresa.classes.*;
import br.com.aula.empresa.repository.FreteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FreteService {

    @Autowired
    private FreteRepository freteRepository;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private FuncionarioService funcionarioService;
    @Autowired
    private EstadoService estadoService;

    public Frete criar(Frete frete) {
        Frete frete1 = freteRepository.save(frete);
        frete1.setOrigem(cidadeService.listarPorId(frete1.getOrigem().getId()));
        frete1.setDestino(cidadeService.listarPorId(frete1.getDestino().getId()));
        frete1.setRemetente(clienteService.listarPorId(frete1.getRemetente().getId()));
        frete1.setDestinatario(clienteService.listarPorId(frete1.getDestinatario().getId()));
        frete1.setFuncionario(funcionarioService.listarPorId(frete1.getFuncionario().getId()));
        return frete1;
    }

    public List<Frete> listar() {
        return freteRepository.findAll();
    }

    public Frete listarPorId(Long id) {
        return freteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Frete não encontrado."));
    }

    @Transactional
    public Frete atualizar(Long id, Frete frete1) {
        Frete frete = freteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Frete não encontrado."));
        frete.setQuemPaga(frete1.getQuemPaga());
        frete.setNumeroConhecimento(frete1.getNumeroConhecimento());
        frete.setPeso(frete1.getPeso());
        frete.setValor(frete1.getValor());
        frete.setIcms(frete1.getIcms());
        frete.setPedagio(frete1.getIcms());
        frete.setDataFrete(frete1.getDataFrete());
        frete.setOrigem(cidadeService.listarPorId(frete1.getOrigem().getId()));
        frete.setDestino(cidadeService.listarPorId(frete1.getDestino().getId()));
        frete.setRemetente(clienteService.listarPorId(frete1.getRemetente().getId()));
        frete.setDestinatario(clienteService.listarPorId(frete1.getDestinatario().getId()));
        frete.setFuncionario(funcionarioService.listarPorId(frete1.getFuncionario().getId()));
        return frete;
    }

    @Transactional
    public void deletar(Long id) {
        freteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Frete não encontrado."));
        freteRepository.deleteById(id);
    }

    public List<ArrecadacaoDTO> calcularArrecadacaoPorDestino(Long id, String ano) {
        estadoService.listarPorId(id);
        if (ano.isBlank() || ano.length() != 4) {
            throw new RuntimeException("Ano inválido.");
        };
        return freteRepository.findAllArrecadacaoByDestino(id, ano);
    }

    public List<MediaFretesDTO> calcularMediaFreteOrigemDestinoPorCidadeEstado(Long id) {
        estadoService.listarPorId(id);
        return freteRepository.findFreteMediaPorEstado(id);
    }

    public List<FretePJDTO> calcularFretesAtendidosPorPJNoMesEAno(String mes, String ano) {
        int mesNumber = Integer.parseInt(mes);
        if (mesNumber <= 0 || mesNumber >= 13) {
            throw new RuntimeException("Mês inválido.");
        }
        if (ano.isBlank() || ano.length() != 4) {
            throw new RuntimeException("Ano inválido.");
        }
        return freteRepository.findFretesAtendidosPorPJNoMesEAno(mes, ano);
    }
}
