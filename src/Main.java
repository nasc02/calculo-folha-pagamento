import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.Month;
public class Main {
    public static void main(String[] args) {

        final double VALOR_MAXIMO_DEDUCAO_IRRF = 869.36;
        final double VALOR_MAXIMO_DEDUCAO_INSS = 877.24;
        final double FGTS_PORCENTAGEM = 0.08;
        final double PERICULOSIDADE_PORCENTAGEM_FIXA = 0.30;
        final double INSALUBRIDADE_PORCENTAGEM_FAIXA_1 = 0.10;
        final double INSALUBRIDADE_PORCENTAGEM_FAIXA_2 = 0.20;
        final double INSALUBRIDADE_PORCENTAGEM_FAIXA_3 = 0.40;
        final double PERCENTUAL_DESCONTO_MAXIMO_FUNCIONARIO = 0.30;

        int cargaHorariaDia = 0;
        int diasTrabalhadosSemana = 0;
        int cargaHorariaSemana = 0;
        int totalHorasTrabalhadasMes = 0;
        int periculosidade = 0;
        int insalubridade = 0;
        int inssFaixa = 0;
        int dependentes = 0;

        double valorBonusPericulosidade = 0;
        double valorBonusInsalubridade = 0;
        double salarioBruto = 0;
        double salarioHora = 0;
        double valeTransportePorcentagem = 0;
        double valeTransporte = 0;
        double valeAlimentacaoPorcentagem = 0;
        double valeAlimentacao = 0;
        double salarioLiquido = 0;
        double inssValor1 = 0;
        double inssValor2 = 0;
        double inssValor3 = 0;
        double inssValor4 = 0;
        double aliquotaEfetivaInss = 0;
        double valorAliquotaEfetivaInss = 0;
        double fgts = 0;
        double salarioBaseCalculo= 0;
        double valorDeducaoDependentes = 0;
        double pensaoAlimenticia = 0;
        double outrasDeducoes = 0;
        double totalDeducoes = 0;
        double aliquotaIrrf = 0;
        double incidenciaMensalIrrf = 0;
        double descontoIrrf = 0;
        double aliquotaEfetivaIrrf = 0;
        double valorAliquotaEfetivaIrrf  = 0;
        double insalubridadePorcentagem = 0;
        double periculosidadePorcentagem = 0;
        double descontoMaximoFuncionario = 0;
        double salarioLiquidoMinimo = 0;
        double descontoTotalSalario = 0;

        String nomeFuncionario = "";
        String dataAdmissaoString = "";
        String dataAdmissaoFormatada = "";
        String nomePtBrMesAtual = "";
        String cargoFuncionario = "";
        String horizontal = "──────────────────────────────────────────────────────────────────────────────────────";
        String formatoCaixa = "%c%-86s%c%n";

        char vertical = '│';
        char cimaEsquerda = '┌';
        char cimaDireita = '┐';
        char baixoEsquerda = '└';
        char baixoDireita = '┘';

        Scanner s = new Scanner(System.in);

            System.out.println(cimaEsquerda + horizontal + cimaDireita);
            System.out.printf(formatoCaixa, vertical, "Insira o nome do funcionário:", vertical);
            System.out.println(baixoEsquerda + horizontal + baixoDireita);
            nomeFuncionario = s.nextLine();

            System.out.println(cimaEsquerda + horizontal + cimaDireita);
            System.out.printf(formatoCaixa, vertical, "Informe a data de admissão do funcionário(dd/MM/yyyy): ", vertical);
            System.out.println(baixoEsquerda + horizontal + baixoDireita);
            dataAdmissaoString = s.nextLine();
            DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataAdmissao = LocalDate.parse(dataAdmissaoString, formatoData);
            dataAdmissaoFormatada = dataAdmissao.format(formatoData);
            Month mesAtual = LocalDate.now().getMonth();
            nomePtBrMesAtual = mesAtual.getDisplayName(TextStyle.FULL, Locale.getDefault());

            System.out.println(cimaEsquerda + horizontal + cimaDireita);
            System.out.printf(formatoCaixa, vertical, "Informe o cargo do funcionário: " , vertical);
            System.out.println(baixoEsquerda + horizontal + baixoDireita);
            cargoFuncionario = s.nextLine();

            System.out.println(cimaEsquerda + horizontal + cimaDireita);
            System.out.printf(formatoCaixa, vertical, "Insira o salário bruto do funcionário: " , vertical);
            System.out.println(baixoEsquerda + horizontal + baixoDireita);
            salarioBruto = s.nextDouble();

            System.out.println(cimaEsquerda + horizontal + cimaDireita);
            System.out.printf(formatoCaixa, vertical, "Insira a carga horária(subtraia o horário de descanso) diária do funcionário: " , vertical);
            System.out.println(baixoEsquerda + horizontal + baixoDireita);
            cargaHorariaDia = s.nextInt();

            System.out.println(cimaEsquerda + horizontal + cimaDireita);
            System.out.printf(formatoCaixa, vertical, "Insira a quantidade de dias trabalhados na semana do funcionário: " , vertical);
            System.out.println(baixoEsquerda + horizontal + baixoDireita);
            diasTrabalhadosSemana = s.nextInt();

            System.out.println(cimaEsquerda + horizontal + cimaDireita);
            System.out.printf(formatoCaixa, vertical, "O funcionário: Recebe bônus de periculosidade?(Responda 1 para sim e 0 para não)", vertical);
            System.out.println(baixoEsquerda + horizontal + baixoDireita);
            periculosidade = s.nextInt();
            if(periculosidade == 1){
                valorBonusPericulosidade = salarioBruto * PERICULOSIDADE_PORCENTAGEM_FIXA;
                periculosidadePorcentagem = PERICULOSIDADE_PORCENTAGEM_FIXA;
            }else{
                valorBonusPericulosidade = 0;
                periculosidadePorcentagem = 0;
            }

            System.out.println(cimaEsquerda + horizontal + "───────────────────────────────" + cimaDireita);
            System.out.printf(formatoCaixa, vertical, "O funcionário: Recebe bônus de insalubridade?(CONSIDERE 0=Não Recebe, 1 = Baixo(10%),  2 = Médio(20%), 3 =  Alto(40%)", vertical);
            System.out.println(baixoEsquerda + horizontal +  "───────────────────────────────" + baixoDireita);
            insalubridade = s.nextInt();
            if(insalubridade == 1){
                valorBonusInsalubridade = salarioBruto * INSALUBRIDADE_PORCENTAGEM_FAIXA_1;
                insalubridadePorcentagem = INSALUBRIDADE_PORCENTAGEM_FAIXA_1;
            }else if(insalubridade == 2){
                valorBonusInsalubridade = salarioBruto * INSALUBRIDADE_PORCENTAGEM_FAIXA_2;
                insalubridadePorcentagem = INSALUBRIDADE_PORCENTAGEM_FAIXA_2;
            }else if(insalubridade == 3){
                valorBonusInsalubridade = salarioBruto * INSALUBRIDADE_PORCENTAGEM_FAIXA_3;
                insalubridadePorcentagem = INSALUBRIDADE_PORCENTAGEM_FAIXA_3;
            }else{
                valorBonusInsalubridade = 0;
                insalubridadePorcentagem = 0;
            }

            salarioBruto = salarioBruto + valorBonusInsalubridade + valorBonusPericulosidade;
            fgts = salarioBruto * FGTS_PORCENTAGEM;

            cargaHorariaSemana = cargaHorariaDia * diasTrabalhadosSemana;
            totalHorasTrabalhadasMes = cargaHorariaSemana * 4;

            salarioHora = salarioBruto / totalHorasTrabalhadasMes;

            System.out.println(cimaEsquerda + horizontal + "─────────────────────────────────────────" + cimaDireita);
            System.out.printf(formatoCaixa, vertical, "escreva a porcentagem do salário descontada para o vale transporte do funcionário(caso não possua vale transporte, escreva 0): ", vertical);
            System.out.println(baixoEsquerda + horizontal+ "─────────────────────────────────────────" + baixoDireita);
            valeTransportePorcentagem = s.nextDouble();
            valeTransportePorcentagem = valeTransportePorcentagem / 100;
            valeTransporte = salarioBruto * valeTransportePorcentagem;

            System.out.println(cimaEsquerda + horizontal + "───────────────────────────────────────────────────────────────" +  cimaDireita);
            System.out.printf(formatoCaixa, vertical, "escreva a porcentagem do salário descontada para o vale alimentação do funcionário(caso não possua vale alimentaçao ou nao seja cobrado, escreva 0): " , vertical);
            System.out.println(baixoEsquerda + horizontal + "───────────────────────────────────────────────────────────────" + baixoDireita);
            valeAlimentacaoPorcentagem = s.nextDouble();
            valeAlimentacaoPorcentagem = valeAlimentacaoPorcentagem / 100;
            valeAlimentacao = salarioBruto * valeAlimentacaoPorcentagem;

            System.out.println(cimaEsquerda + horizontal + "──────────────────────────────────────────────" + cimaDireita);
            System.out.printf(formatoCaixa, vertical, "Em qual faixa de contribuiçao do INSS o funcionario se encontra(Lembre-se, vai de 1 a 4, consulte a tabela de contribuiçao do INSS):", vertical);
            System.out.println(baixoEsquerda + horizontal + "──────────────────────────────────────────────" + baixoDireita);
            inssFaixa = s.nextInt();
            switch (inssFaixa){
                case 1:
                    inssValor1 = 1302.00 * 0.075;
                    aliquotaEfetivaInss = inssValor1 / salarioBruto;
                    break;
                case 2:
                    inssValor1 = 1302.00 * 0.075;
                    inssValor2 = 1269.29 * 0.09;
                    aliquotaEfetivaInss = (inssValor1 + inssValor2) / salarioBruto;
                    break;
                case 3:
                    inssValor1 = 1302.00 * 0.075;
                    inssValor2 = 1269.29 * 0.09;
                    inssValor3 = 1285.65 * 0.12;
                    aliquotaEfetivaInss = (inssValor1 + inssValor2 + inssValor3) / salarioBruto;
                    break;
                case 4:
                    inssValor1 = 1302.00 * 0.075;
                    inssValor2 = 1269.29 * 0.09;
                    inssValor3 = 1285.65 * 0.12;
                    inssValor4 = 3650.55 * 0.14;
                    aliquotaEfetivaInss = (inssValor1 + inssValor2 + inssValor3 + inssValor4) / salarioBruto;
                    break;
                default:
                    System.out.println(cimaEsquerda + horizontal + cimaDireita);
                    System.out.printf(formatoCaixa, vertical, "O caso informado não existe.", vertical);
                    System.out.println(baixoEsquerda + horizontal + baixoDireita);
            }
            valorAliquotaEfetivaInss = salarioBruto * aliquotaEfetivaInss;

            if(valorAliquotaEfetivaInss > VALOR_MAXIMO_DEDUCAO_INSS){
                valorAliquotaEfetivaInss = VALOR_MAXIMO_DEDUCAO_INSS;
            }

            System.out.println(cimaEsquerda + horizontal + cimaDireita);
            System.out.printf(formatoCaixa, vertical, "O funcionário possui quantos dependentes?", vertical);
            System.out.println(baixoEsquerda + horizontal + baixoDireita);
            dependentes = s.nextInt();
            valorDeducaoDependentes = dependentes * 189.59;
            System.out.println(cimaEsquerda + horizontal + cimaDireita);
            System.out.printf(formatoCaixa, vertical, "O funcionário paga quanto de pensão alimenticia, caso não pague digite 0?", vertical);
            System.out.println(baixoEsquerda + horizontal + baixoDireita);
            pensaoAlimenticia = s.nextDouble();
            System.out.println(cimaEsquerda + horizontal + cimaDireita);
            System.out.printf(formatoCaixa, vertical, "informe o valor de quaisquer outras deduções do IRRF abaixo, caso não possua digite 0", vertical);
            System.out.println(baixoEsquerda + horizontal + baixoDireita);
            outrasDeducoes = s.nextDouble();
            totalDeducoes = valorAliquotaEfetivaInss + valorDeducaoDependentes + pensaoAlimenticia + outrasDeducoes;
            salarioBaseCalculo = salarioBruto - totalDeducoes;
            if(salarioBruto <= 1903.98){
                aliquotaIrrf = 0;
            }else if(salarioBruto >= 1903.99 && salarioBruto <= 2826.65){
                aliquotaIrrf = 0.075;
            }else if(salarioBruto >= 2826.66 && salarioBruto <= 3751.05){
                aliquotaIrrf = 0.15;
            }else if(salarioBruto >= 3751.06 && salarioBruto <= 4664.68){
                aliquotaIrrf = 0.225;
            }else if(salarioBruto > 4664.68){
                aliquotaIrrf = 0.275;
            }else{
                System.out.println(cimaEsquerda + horizontal + cimaDireita);
                System.out.printf(formatoCaixa, vertical, "Houve algum erro, recomeçe.", vertical);
                System.out.println(baixoEsquerda + horizontal + baixoDireita);

            }

            if(totalDeducoes > VALOR_MAXIMO_DEDUCAO_IRRF){
                totalDeducoes = VALOR_MAXIMO_DEDUCAO_IRRF;
            }

            if(aliquotaIrrf == 0){
                System.out.println(cimaEsquerda + horizontal + cimaDireita);
                System.out.printf(formatoCaixa, vertical, "O funcionário é isento de IRRF.", vertical);
                System.out.println(baixoEsquerda + horizontal + baixoDireita);
            }else{
                incidenciaMensalIrrf = salarioBaseCalculo * aliquotaIrrf;
                descontoIrrf = incidenciaMensalIrrf - totalDeducoes;
                aliquotaEfetivaIrrf = descontoIrrf / salarioBruto;
                valorAliquotaEfetivaIrrf = salarioBruto * aliquotaEfetivaIrrf;
            }

            salarioLiquido = salarioBruto - (valeTransporte + valeAlimentacao + valorAliquotaEfetivaInss + valorAliquotaEfetivaIrrf);
            descontoMaximoFuncionario = salarioBruto * PERCENTUAL_DESCONTO_MAXIMO_FUNCIONARIO;
            descontoTotalSalario = valeTransporte + valeAlimentacao + valorAliquotaEfetivaInss + valorAliquotaEfetivaIrrf;
            salarioLiquidoMinimo = salarioLiquido - descontoMaximoFuncionario;
            if (salarioLiquido < salarioLiquidoMinimo){
                salarioLiquido = salarioLiquidoMinimo;
            }
            if(descontoTotalSalario > descontoMaximoFuncionario){
                descontoTotalSalario = descontoMaximoFuncionario;
            }


        System.out.println(cimaEsquerda + horizontal + cimaDireita);
        System.out.printf(formatoCaixa, vertical,"                                 FOLHA DE PAGAMENTO", vertical);
        System.out.println(baixoEsquerda + horizontal + baixoDireita);
        System.out.printf("| Funcionário: %-71s |\n", nomeFuncionario);
        System.out.printf("| Data de admissão: %-66s |\n", dataAdmissaoFormatada);
        System.out.printf("| Mês de referencia: %-65s |\n", nomePtBrMesAtual);
        System.out.printf("| Cargo funcionário: %-65s |\n", cargoFuncionario);
        System.out.printf("| Salário Liquido: %-67s |\n", String.format("%.2f",salarioLiquido) + "R$");
        System.out.printf("| Salário Bruto: %-69s |\n", String.format("%.2f",salarioBruto) + "R$");
        System.out.printf("| Salário por Hora: %-66s |\n", String.format("%.2f",salarioHora) + "R$");
        System.out.printf("| Salário por Hora: %-66s |\n", String.format("%.2f",salarioHora) + "R$");
        System.out.println(baixoEsquerda + horizontal + baixoDireita);

        System.out.println();

        System.out.println(cimaEsquerda + horizontal + cimaDireita);
        System.out.printf(formatoCaixa, vertical,"                                      PROVENTOS", vertical);
        System.out.println(baixoEsquerda + horizontal + baixoDireita);
        System.out.printf("| Adicional de insalubridade: %-56s |\n", String.format("%.2f",valorBonusInsalubridade) + "R$");
        System.out.printf("| Percentual insalubridade: %-58s |\n", String.format("%.2f%%", insalubridadePorcentagem * 100));
        System.out.printf("| Adicional de periculosidade: %-55s |\n", String.format("%.2f",valorBonusPericulosidade) + "R$");
        System.out.printf("| Percentual periculosidade: %-57s |\n", String.format("%.2f%%", periculosidadePorcentagem * 100));
        System.out.printf("| FGTS: %-78s |\n", String.format("%.2f",fgts) + "R$");
        System.out.printf("| Percentual FGTS: %-67s |\n", String.format("%.2f%%", FGTS_PORCENTAGEM * 100));
        System.out.println(baixoEsquerda + horizontal + baixoDireita);

        System.out.println();

        System.out.println(cimaEsquerda + horizontal + cimaDireita);
        System.out.printf(formatoCaixa, vertical,"                           BASE DE CÁLCULO INSS/FGTS/IRRF", vertical);
        System.out.println(baixoEsquerda + horizontal + baixoDireita);
        System.out.printf("| Deduções IRRF: %-69s |\n", String.format("%.2f",totalDeducoes) + "R$");
        System.out.printf("| Salário base cálculo IRRF: %-57s |\n", String.format("%.2f",salarioBaseCalculo) + "R$");
        System.out.printf("| Aliquota cálculo IRRF: %-61s |\n", String.format("%.2f%%", aliquotaIrrf * 100));
        System.out.printf("| Aliquota cálculo FGTS: %-61s |\n", String.format("%.2f%%", FGTS_PORCENTAGEM * 100));
        System.out.printf("| Aliquota cálculo INSS: %-61s |\n", String.format("%.2f%%", aliquotaEfetivaInss * 100));
        System.out.println(baixoEsquerda + horizontal + baixoDireita);

        System.out.println();

        System.out.println(cimaEsquerda + horizontal + cimaDireita);
        System.out.printf(formatoCaixa, vertical,"                                      DESCONTOS", vertical);
        System.out.println(baixoEsquerda + horizontal + baixoDireita);
        System.out.printf("| INSS: %-78s |\n", String.format("%.2f",valorAliquotaEfetivaInss) + "R$");
        System.out.printf("| IRRF: %-78s |\n", String.format("%.2f",valorAliquotaEfetivaIrrf) + "R$");
        System.out.printf("| Vale transporte: %-67s |\n", String.format("%.2f",valeTransporte) + "R$");
        System.out.printf("| Vale alimentação: %-66s |\n", String.format("%.2f",valeAlimentacao) + "R$");
        System.out.printf("| Total de descontos(máximo 30%%): %-52s |\n", String.format("%.2f",descontoTotalSalario) + "R$");
        System.out.println(baixoEsquerda + horizontal + baixoDireita);

    }
}