package cadastrobd;
/**
 *
 * @author weverlin
 */

import cadastrobd.model.PessoaFisica;
import cadastrobd.model.dao.PessoaFisicaDAO;

public class CadastroBD {

    public static void main(String[] args) {
        try {
            PessoaFisicaDAO dao = new PessoaFisicaDAO();

            
            PessoaFisica pessoaFisica = new PessoaFisica();
            pessoaFisica.setNome("João Silva");
            pessoaFisica.setLogradouro("Rua A");
            pessoaFisica.setCidade("Cidade B");
            pessoaFisica.setEstado("Estado C");
            pessoaFisica.setTelefone("123456789");
            pessoaFisica.setEmail("joao.silva@example.com");
            pessoaFisica.setCpf("111.111.111-11");

            
            dao.incluir(pessoaFisica);

            
            PessoaFisica pessoaConsultada = dao.getPessoa(pessoaFisica.getId());
            System.out.println(pessoaConsultada);

            
            pessoaConsultada.setNome("João da Silva");
            dao.alterar(pessoaConsultada);

            dao.excluir(pessoaConsultada.getId());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
