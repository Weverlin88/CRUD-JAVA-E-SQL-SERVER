package cadastrobd;

import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.dao.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridicaDAO;

import java.sql.SQLException;

public class CadastroBDTeste {

    public static void main(String[] args) {
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();

        try {
            
            PessoaFisica pf = new PessoaFisica(1, "João Silva", "Rua A", "Cidade B", "Estado C", "123456789", "joao@example.com", "111.111.111-11");
            pessoaFisicaDAO.incluir(pf);

            pf.setNome("João Silva Alterado");
            pessoaFisicaDAO.alterar(pf);

            for (PessoaFisica pessoa : pessoaFisicaDAO.getPessoas()) {
                pessoa.exibir();
            }

            pessoaFisicaDAO.excluir(pf.getId());

            
            PessoaJuridica pj = new PessoaJuridica(2, "Empresa X", "Avenida Y", "Cidade Z", "Estado W", "987654321", "empresa@example.com", "22.222.222/0001-22");
            pessoaJuridicaDAO.incluir(pj);

            pj.setNome("Empresa X Alterada");
            pessoaJuridicaDAO.alterar(pj);

            for (PessoaJuridica pessoa : pessoaJuridicaDAO.getPessoas()) {
                pessoa.exibir();
            }

            pessoaJuridicaDAO.excluir(pj.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
