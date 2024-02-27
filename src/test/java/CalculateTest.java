import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.ListValue;
import com.automationanywhere.botcommand.data.impl.NumberValue;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.data.impl.TableValue;
import com.automationanywhere.botcommand.data.model.Schema;
import com.automationanywhere.botcommand.data.model.table.Row;
import com.automationanywhere.botcommand.data.model.table.Table;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.botcommand.samples.commands.basic.Calculate;
import com.automationanywhere.botcommand.samples.commands.basic.ColumnToList;
import com.automationanywhere.botcommand.samples.commands.basic.JsonListToTable;
import com.automationanywhere.botcommand.samples.commands.basic.ListToHeaders;
import com.automationanywhere.botcommand.samples.commands.conditionals.ColCount;
import com.automationanywhere.botcommand.samples.commands.conditionals.HasHeader;
import com.automationanywhere.botcommand.samples.commands.conditionals.RowCount;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CalculateTest {

    @Test
    public void json(){
        JsonListToTable jsn = new JsonListToTable();
        String jsonText = "[{\"solicitante1\":\"['Risco Time']\",\"tipo_de_solicita_o\":\"Outro (Erro ou Dúvida)\",\"categoria\":\"Organizador/Evento\",\"n_do_evento_obrigat_rio\":\"2243246 \",\"explique_o_que_voc_precisa\":\"Gentileza recuperar perda do evento 2243246, utilizando saldo retido do evento 2305159, conforme pipes abaixo.'871462339'871463873'871464982'871465855'871526144'871527031'871527940'871528617'871533564'871534544'871535868'871536726''\",\"resolveu_o_card_insira_aqui_suas_observa_es\":\"Ana está verificando.\"},{\"solicitante1\":\"['Leticia Si']\",\"tipo_de_solicita_o\":\"Solicitação\",\"categoria\":\"Comprador/Pedido\",\"solicita_o_comprador_pedido\":\"Comprovante de reembolso\",\"unidade_de_neg_cio\":\"Bileto\",\"n_do_evento_obrigat_rio\":\"JETHRO TULL´S MARTIN BARRE - 50 ANOS DE AQUALUNG Teatro Liberdade\",\"n_do_pedido_obrigat_rio\":\"32618434\",\"explique_o_que_voc_precisa\":\"Migos, podem verificar se houve reembolso referente ao pedido S32618434, por gentileza?'''RETORNO ZEEV 74217 - 'Miga, o pedido não foi reembolsado via API. Gentileza seguir com o reembolso manualmente por Finanças, mas verificar PRIMEIRO se já não foi feito por lá.'\",\"valor_do_reembolso_obrigat_rio\":\"696.00\",\"n_pipefy\":\"0\",\"attachment_t_tulo\":\"['https://app.pipefy.com/storage/v1/signed/uploads/2ebef481-dc08-42e8-96a0-67708b635f25/ZEEV74217.PNG?expires_on=1709057849&signature=KiglIpLsWoyQCtzPPwqBybHdl8j0%2B1lmycXcdFW9tdU%3D']\"},{\"solicitante1\":\"['Juliana Santos Carvalho']\",\"tipo_de_solicita_o\":\"Outro (Erro ou Dúvida)\",\"categoria\":\"Comprador/Pedido\",\"n_do_evento_obrigat_rio\":\"2259851\",\"explique_o_que_voc_precisa\":\"Migos, devido ao erro do Sympla Streaming no dia 16/09/2023, a diretoria ofereceu para os produtores afetados que entraram em contato conosco, a devolução do valor da tava do evento atingindo é do próximo evento criado na plataforma. Neste caso preciso que o valor da taxa referente ao evento 2259851 - OAB SEM MEDO! INTENSIVO 1ª FASE EXAME 40 seja devolvida ao produtor. Segue abaixo os dados bancários para devolução: ''Nome: Angela Almanara da Silva'Banco:  Bradesco'AG: 0311'CONTA: 61224-3 'CPF: 258.315.068-51'Valor:  R$ 608,79\",\"existe_alguma_pend_ncia_no_card\":\"Não\",\"o_card_deve_ser_cancelado\":\"Não\",\"resolveu_o_card_insira_aqui_suas_observa_es\":\"Pagamento programado para o dia 28/02.\"},{\"solicitante1\":\"['tsp.mayara.pertele']\",\"tipo_de_solicita_o\":\"Solicitação\",\"categoria\":\"Comprador/Pedido\",\"solicita_o_comprador_pedido\":\"Reembolso > 180 dias\",\"n_do_evento_obrigat_rio\":\"1987844\",\"copy_of_n_do_pedido_obrigat_rio\":\"1SL845Z7GBG\",\"explique_o_que_voc_precisa\":\"migos erro pix\",\"attachment_t_tulo\":\"[]\",\"tipo_de_conta\":\"Conta-corrente\",\"nome_da_pessoa_titular_da_conta\":\"Leire Augusto Campiteli de Azevedo\",\"cpf\":\"419.996.018-07\",\"banco\":\"Nu Pagamentos\",\"c_digo_do_banco_n_o_obrigat_rio\":\"260\",\"ag_ncia_sem_digito_obrigat_rio\":\"0001\",\"conta_sem_digito_obrigat_rio\":\"51948055\",\"digito_da_conta_obrigat_rio\":\"5\",\"valor_do_reembolso_obrigat_rio\":\"308.00\"},{\"solicitante1\":\"['tsp.mayara.pertele']\",\"tipo_de_solicita_o\":\"Solicitação\",\"categoria\":\"Comprador/Pedido\",\"solicita_o_comprador_pedido\":\"Reembolso > 180 dias\",\"n_do_evento_obrigat_rio\":\"1788869\",\"copy_of_n_do_pedido_obrigat_rio\":\"1MYU5556Y43\",\"explique_o_que_voc_precisa\":\"migos erro 180 dias\",\"attachment_t_tulo\":\"[]\",\"tipo_de_conta\":\"Conta-corrente\",\"nome_da_pessoa_titular_da_conta\":\"Andre Junior Vargas mazzucatto\",\"cpf\":\"056.519.559-09\",\"banco\":\"Banco do Brasil\",\"c_digo_do_banco_n_o_obrigat_rio\":\"001\",\"ag_ncia_sem_digito_obrigat_rio\":\"3004\",\"conta_sem_digito_obrigat_rio\":\"19601\",\"digito_da_conta_obrigat_rio\":\"0\",\"valor_do_reembolso_obrigat_rio\":\"332.75\"},{\"solicitante1\":\"['PAULA Pa']\",\"tipo_de_solicita_o\":\"Solicitação\",\"categoria\":\"Comprador/Pedido\",\"solicita_o_comprador_pedido\":\"Inserção manual de Reembolso\",\"n_do_evento_obrigat_rio\":\"1851283\",\"n_do_pedido_obrigat_rio\":\"1ZFSJ5YL3DY\",\"attachment_t_tulo\":\"[]\",\"tipo_de_conta\":\"Conta-corrente\",\"nome_da_pessoa_titular_da_conta\":\"Anderson Silveira Soares \",\"cpf_ou_cnpj_apenas_n_meros\":\"80566561620\",\"banco\":\"Caixa Economica\",\"c_digo_do_banco_n_o_obrigat_rio\":\"104\",\"ag_ncia_sem_digito_obrigat_rio\":\"3696\",\"conta_sem_digito_obrigat_rio\":\"00021815\",\"digito_da_conta_obrigat_rio\":\"6\",\"valor_do_reembolso_obrigat_rio\":\"1,274.15\",\"e_mail_da_compra\":\"msilveirasoares@gmail.com\",\"e_mail_para_envio_dos_dados\":\"msilveirasoares@gmail.com\"},{\"solicitante1\":\"['tsp.jessica.oliveira']\",\"tipo_de_solicita_o\":\"Solicitação\",\"categoria\":\"Comprador/Pedido\",\"solicita_o_comprador_pedido\":\"Complemento de Taxa - BILETO\",\"nome_do_evento\":\"MOLEJO - 10/03/24 Araújo Vianna\",\"copy_of_n_do_pedido_obrigat_rio\":\"36355392\",\"explique_o_que_voc_precisa\":\"Cliente exige o reembolso da taxa pois o evento foi alterado.\",\"attachment_t_tulo\":\"[]\",\"tipo_de_conta\":\"Conta-corrente\",\"nome_da_pessoa_titular_da_conta\":\"Gisele Silva da Silva\",\"cpf\":\"501.517.620-15\",\"banco\":\"Itaú\",\"c_digo_do_banco_n_o_obrigat_rio\":\"341\",\"ag_ncia_sem_digito_obrigat_rio\":\"6211\",\"conta_sem_digito_obrigat_rio\":\"05566\",\"digito_da_conta_obrigat_rio\":\"9\",\"valor_da_taxa_a_ser_reembolsado\":\"19.50\"},{\"solicitante1\":\"['Jessica Sanches']\",\"tipo_de_solicita_o\":\"Solicitação\",\"categoria\":\"Comprador/Pedido\",\"solicita_o_comprador_pedido\":\"Complemento de Taxa - BILETO\",\"nome_do_evento\":\"CABARET\",\"copy_of_n_do_pedido_obrigat_rio\":\"S38004881\",\"explique_o_que_voc_precisa\":\"Cliente deseja o reembolso da taxa devido o CDC\",\"attachment_t_tulo\":\"[]\",\"tipo_de_conta\":\"Conta-corrente\",\"nome_da_pessoa_titular_da_conta\":\"Gabriella Maria Back Bebiano Montini\",\"cpf\":\"415.254.158-08\",\"banco\":\"Itaú\",\"c_digo_do_banco_n_o_obrigat_rio\":\"341\",\"ag_ncia_sem_digito_obrigat_rio\":\"2958\",\"conta_sem_digito_obrigat_rio\":\"07358\",\"digito_da_conta_obrigat_rio\":\"3\",\"valor_da_taxa_a_ser_reembolsado\":\"90.00\"},{\"respons_vel\":\"[]\",\"solicitante1\":\"['TATIANA AL']\",\"tipo_de_solicita_o\":\"Solicitação\",\"categoria\":\"Organizador/Evento\",\"solicita_o_organizador_evento\":\"Confirmar recebimento de transferência\",\"n_do_evento_obrigat_rio\":\"2280316\",\"explique_o_que_voc_precisa\":\"Olá pessoal, tudo  bem? Podem confirmar o recebimento do valor em anexo, por favor? Caso seja confirmado, favor reverter a perda do pedido 25YRS6PFK0H por favor.\",\"comprovante_do_valor_recebido_na_conta\":\"['https://app.pipefy.com/storage/v1/signed/uploads/bd14bb04-3415-450f-9fe8-cbf2f35982ad/IMG_4181.jpg?expires_on=1709057849&signature=v1C0E2wGFp9esF263YupxuEHNqDx05saZkg14SbjnkU%3D']\"},{\"solicitante1\":\"['tsp.jessica.oliveira']\",\"tipo_de_solicita_o\":\"Solicitação\",\"categoria\":\"Comprador/Pedido\",\"solicita_o_comprador_pedido\":\"Falha Reembolso Cartão  - BILETO\",\"copy_of_n_do_pedido_obrigat_rio\":\"37809939\",\"explique_o_que_voc_precisa\":\"Falha de reembolso via PIX\",\"attachment_t_tulo\":\"[]\",\"tipo_de_conta\":\"Conta-corrente\",\"nome_da_pessoa_titular_da_conta\":\"Mariana Fraga Santarelli Duran Lopes\",\"cpf\":\"381.023.138-03\",\"banco\":\"Bradesco\",\"c_digo_do_banco_n_o_obrigat_rio\":\"237\",\"ag_ncia_sem_digito_obrigat_rio\":\"002\",\"conta_sem_digito_obrigat_rio\":\"65515\",\"digito_da_conta_obrigat_rio\":\"5\",\"valor_do_ingresso_sem_taxa\":\"140.00\",\"valor_taxa_sympla\":\"21.00\",\"valor_do_reembolso_obrigat_rio\":\"161.00\"},{\"solicitante1\":\"['Naiane Ar']\",\"tipo_de_solicita_o\":\"Solicitação\",\"categoria\":\"Comprador/Pedido\",\"solicita_o_comprador_pedido\":\"Perda Financeira Plataforma\",\"n_do_evento_obrigat_rio\":\"2257296\",\"n_do_pedido_obrigat_rio\":\"24SCG6NAVBC\",\"teste_quem_o_respons_vel_pela_perda\":\"Outros\",\"por_que_a_sympla_deve_absorver_a_perda\":\"Perdemos o prazo de repasse do organizador do evento 2239014 e não conseguimos reter o valor.\",\"como_o_reembolso_deve_acontecer\":\"Transferência bancária \",\"tipo_de_conta\":\"Conta-corrente\",\"nome_da_pessoa_titular_da_conta\":\"Djordyan Hendrick Cardoso Cordeiro\",\"cpf_ou_cnpj_apenas_n_meros\":\"15158345651\",\"banco\":\"Banco do Brasil\",\"c_digo_do_banco_n_o_obrigat_rio\":\"001\",\"ag_ncia_sem_digito_obrigat_rio\":\"7135\",\"conta_sem_digito_obrigat_rio\":\"14515\",\"digito_da_conta_obrigat_rio\":\"7\",\"copy_of_valor_do_reembolso_obrigat_rio\":\"302.40\"},{\"solicitante1\":\"['Naiane Ar']\",\"tipo_de_solicita_o\":\"Solicitação\",\"categoria\":\"Comprador/Pedido\",\"solicita_o_comprador_pedido\":\"Perda Financeira Plataforma\",\"n_do_evento_obrigat_rio\":\"2257296\",\"n_do_pedido_obrigat_rio\":\"24SCG6N9HR1\",\"teste_quem_o_respons_vel_pela_perda\":\"Outros\",\"por_que_a_sympla_deve_absorver_a_perda\":\"Perdemos o prazo de repasse do organizador do evento 2239014 e não conseguimos reter o valor.\",\"como_o_reembolso_deve_acontecer\":\"Transferência bancária \",\"tipo_de_conta\":\"Conta-corrente\",\"nome_da_pessoa_titular_da_conta\":\"JACKSON FERREIRA PIRES\",\"cpf_ou_cnpj_apenas_n_meros\":\"05135697667\",\"banco\":\"Banco do Brasil\",\"c_digo_do_banco_n_o_obrigat_rio\":\"001\",\"ag_ncia_sem_digito_obrigat_rio\":\"2532\",\"conta_sem_digito_obrigat_rio\":\"12626\",\"digito_da_conta_obrigat_rio\":\"8\",\"copy_of_valor_do_reembolso_obrigat_rio\":\"459.20\"},{\"solicitante1\":\"['Fabiana Bo']\",\"tipo_de_solicita_o\":\"Solicitação\",\"categoria\":\"Comprador/Pedido\",\"solicita_o_comprador_pedido\":\"Reembolso > 180 dias\",\"n_do_evento_obrigat_rio\":\"2051228\",\"copy_of_n_do_pedido_obrigat_rio\":\"1UJ4S60CYZ8\",\"explique_o_que_voc_precisa\":\"Migos, por gentileza, seguir com o reembolso mais de 180 dias para reembolso via PIX'Part.: stephanyesmer@icloud.com \",\"attachment_t_tulo\":\"[]\",\"tipo_de_conta\":\"Conta-corrente\",\"nome_da_pessoa_titular_da_conta\":\"Stephany Fernanda Esmeraldo Damasceno\",\"cpf\":\"228.321.018-60\",\"banco\":\"Banco: Itaú\",\"c_digo_do_banco_n_o_obrigat_rio\":\"341\",\"ag_ncia_sem_digito_obrigat_rio\":\"6328\",\"conta_sem_digito_obrigat_rio\":\"21623\",\"digito_da_conta_obrigat_rio\":\"6\",\"valor_do_reembolso_obrigat_rio\":\"89.10\"},{\"solicitante1\":\"['tsp.jessica.oliveira']\",\"tipo_de_solicita_o\":\"Solicitação\",\"categoria\":\"Comprador/Pedido\",\"solicita_o_comprador_pedido\":\"Reembolso de Boleto - BILETO\",\"nome_do_evento\":\"DUETOS, A COMÉDIA DE PETER QUILTER Teatro dos 4\",\"copy_of_n_do_pedido_obrigat_rio\":\"37648386\",\"explique_o_que_voc_precisa\":\"Reembolso de boleto\",\"attachment_t_tulo\":\"[]\",\"tipo_de_conta\":\"Conta-corrente\",\"nome_da_pessoa_titular_da_conta\":\"Carlos Alberto Rodrigues Alves\",\"cpf\":\"551.737.137-04\",\"banco\":\"Santander\",\"c_digo_do_banco_n_o_obrigat_rio\":\"033\",\"ag_ncia_sem_digito_obrigat_rio\":\"3003\",\"conta_sem_digito_obrigat_rio\":\"01001186\",\"digito_da_conta_obrigat_rio\":\"9\",\"valor_do_ingresso_sem_taxa\":\"140.00\",\"valor_taxa_sympla\":\"21.00\",\"valor_do_reembolso_obrigat_rio\":\"161.00\"},{\"solicitante1\":\"['tsp.samara.silva']\",\"tipo_de_solicita_o\":\"Solicitação\",\"categoria\":\"Comprador/Pedido\",\"solicita_o_comprador_pedido\":\"Complemento de Taxa - BILETO\",\"nome_do_evento\":\"Histórias do Porchat - Teatro Multiplan - VillageMall\",\"copy_of_n_do_pedido_obrigat_rio\":\"S38023126\",\"explique_o_que_voc_precisa\":\"Participante efetuou o cancelamento no prazo e solicita o estorno da taxa. \",\"attachment_t_tulo\":\"[]\",\"tipo_de_conta\":\"Conta-corrente\",\"nome_da_pessoa_titular_da_conta\":\"Rebecca Gomes de Marco\",\"cpf\":\"153.425.987-29\",\"banco\":\"Nubank\",\"c_digo_do_banco_n_o_obrigat_rio\":\"260\",\"ag_ncia_sem_digito_obrigat_rio\":\"0001\",\"conta_sem_digito_obrigat_rio\":\"67113914\",\"digito_da_conta_obrigat_rio\":\"3\",\"valor_da_taxa_a_ser_reembolsado\":\"15.00\"},{\"solicitante1\":\"['tsp.jessica.oliveira']\",\"tipo_de_solicita_o\":\"Solicitação\",\"categoria\":\"Comprador/Pedido\",\"solicita_o_comprador_pedido\":\"Falha Reembolso Cartão  - BILETO\",\"copy_of_n_do_pedido_obrigat_rio\":\"S32717025\",\"explique_o_que_voc_precisa\":\"Reembolso via PIX com mais de 90 dias.\",\"attachment_t_tulo\":\"[]\",\"tipo_de_conta\":\"Conta-corrente\",\"nome_da_pessoa_titular_da_conta\":\"Luan Rodrigo Pires Fischer\",\"cpf\":\"027.674.010-60\",\"banco\":\"Sicoob\",\"c_digo_do_banco_n_o_obrigat_rio\":\"756\",\"ag_ncia_sem_digito_obrigat_rio\":\"3069\",\"conta_sem_digito_obrigat_rio\":\"401625\",\"digito_da_conta_obrigat_rio\":\"4\",\"valor_do_ingresso_sem_taxa\":\"125.00\",\"valor_taxa_sympla\":\"18.75\",\"valor_do_reembolso_obrigat_rio\":\"143.75\"},{\"solicitante1\":\"['LUCAS Al San']\",\"tipo_de_solicita_o\":\"Solicitação\",\"categoria\":\"Comprador/Pedido\",\"solicita_o_comprador_pedido\":\"Perda Financeira Plataforma\",\"n_do_evento_obrigat_rio\":\"2331846\",\"n_do_pedido_obrigat_rio\":\"275666S31UL\",\"teste_quem_o_respons_vel_pela_perda\":\"Suporte\",\"por_que_a_sympla_deve_absorver_a_perda\":\"Participante não conseguiu participar do evento por causa da organização que barrou ela na porta.\",\"como_o_reembolso_deve_acontecer\":\"Transferência bancária \",\"tipo_de_conta\":\"Conta-corrente\",\"nome_da_pessoa_titular_da_conta\":\"Camile Alves de Oliveira\",\"cpf_ou_cnpj_apenas_n_meros\":\"50714804843\",\"banco\":\"Banco do Brasil\",\"c_digo_do_banco_n_o_obrigat_rio\":\"001\",\"ag_ncia_sem_digito_obrigat_rio\":\"6844\",\"conta_sem_digito_obrigat_rio\":\"44330\",\"digito_da_conta_obrigat_rio\":\"1\",\"copy_of_valor_do_reembolso_obrigat_rio\":\"55.00\"},{\"solicitante1\":\"['Risco Time']\",\"tipo_de_solicita_o\":\"Solicitação\",\"categoria\":\"Comprador/Pedido\",\"solicita_o_comprador_pedido\":\"Perda Financeira Plataforma\",\"n_do_evento_obrigat_rio\":\"2298598\",\"n_do_pedido_obrigat_rio\":\" 264N66ZYC4J\",\"teste_quem_o_respons_vel_pela_perda\":\"Sympla - Cobranças\",\"por_que_a_sympla_deve_absorver_a_perda\":\"Organizador não retornou aos e-mails de cobrança\",\"como_o_reembolso_deve_acontecer\":\"Transferência bancária \",\"tipo_de_conta\":\"Conta-corrente\",\"nome_da_pessoa_titular_da_conta\":\"ANDRE KATSUMI TOYODA\",\"cpf_ou_cnpj_apenas_n_meros\":\"46050893810\",\"banco\":\"Banco- Bradesco\",\"c_digo_do_banco_n_o_obrigat_rio\":\"237\",\"ag_ncia_sem_digito_obrigat_rio\":\"23256\",\"conta_sem_digito_obrigat_rio\":\"0034945\",\"digito_da_conta_obrigat_rio\":\"3\",\"copy_of_valor_do_reembolso_obrigat_rio\":\"45.00\"},{\"solicitante1\":\"['Leandro So']\",\"tipo_de_solicita_o\":\"Solicitação\",\"categoria\":\"Comprador/Pedido\",\"solicita_o_comprador_pedido\":\"Reembolso > 180 dias\",\"n_do_evento_obrigat_rio\":\"1917451 \",\"copy_of_n_do_pedido_obrigat_rio\":\"1QGGB5JB4MB\",\"explique_o_que_voc_precisa\":\"Evento cancelado/alterado\",\"attachment_t_tulo\":\"[]\",\"tipo_de_conta\":\"Conta-corrente\",\"nome_da_pessoa_titular_da_conta\":\"Adriana Pastore De Maria\",\"cpf\":\"385.265.028-32\",\"banco\":\"Itaú\",\"c_digo_do_banco_n_o_obrigat_rio\":\"341\",\"ag_ncia_sem_digito_obrigat_rio\":\"2970\",\"conta_sem_digito_obrigat_rio\":\"18752\",\"digito_da_conta_obrigat_rio\":\"8\",\"valor_do_reembolso_obrigat_rio\":\"275.00\"},{\"solicitante1\":\"['tsp.jessica.oliveira']\",\"tipo_de_solicita_o\":\"Solicitação\",\"categoria\":\"Comprador/Pedido\",\"solicita_o_comprador_pedido\":\"Complemento de Taxa - BILETO\",\"nome_do_evento\":\"CAETANO VELOSO - 02/03/24 Araújo Vianna\",\"copy_of_n_do_pedido_obrigat_rio\":\"37951950\",\"explique_o_que_voc_precisa\":\"Cliente exige o reembolso da taxa de serviço\",\"attachment_t_tulo\":\"[]\",\"tipo_de_conta\":\"Conta-corrente\",\"nome_da_pessoa_titular_da_conta\":\"Carolina Lemos Dias Ferrari\",\"cpf\":\"014.567.070-86\",\"banco\":\"Itaú\",\"c_digo_do_banco_n_o_obrigat_rio\":\"341\",\"ag_ncia_sem_digito_obrigat_rio\":\"3115\",\"conta_sem_digito_obrigat_rio\":\"09719\",\"digito_da_conta_obrigat_rio\":\"2\",\"valor_da_taxa_a_ser_reembolsado\":\"24.52\"},{\"solicitante1\":\"['Fabiana Bo']\",\"tipo_de_solicita_o\":\"Solicitação\",\"categoria\":\"Comprador/Pedido\",\"solicita_o_comprador_pedido\":\"Perda Financeira Plataforma\",\"n_do_evento_obrigat_rio\":\"2324705\",\"n_do_pedido_obrigat_rio\":\"26U716R456Z\",\"teste_quem_o_respons_vel_pela_perda\":\"Suporte\",\"por_que_a_sympla_deve_absorver_a_perda\":\"Migos, por gentileza, seguir com o reembolso como perda, pois o prazo para cancelar o pedido passou despercebido. 'Part.: adilson123768@gmail.com\",\"como_o_reembolso_deve_acontecer\":\"Transferência bancária \",\"tipo_de_conta\":\"Conta-corrente\",\"nome_da_pessoa_titular_da_conta\":\"Adilson Alves dos Santos\",\"cpf_ou_cnpj_apenas_n_meros\":\"05875817518\",\"banco\":\"Banco: Nu Pagamentos S.A\",\"c_digo_do_banco_n_o_obrigat_rio\":\"260\",\"ag_ncia_sem_digito_obrigat_rio\":\"0001\",\"conta_sem_digito_obrigat_rio\":\"7285939\",\"digito_da_conta_obrigat_rio\":\"0\",\"copy_of_valor_do_reembolso_obrigat_rio\":\"30.00\"},{\"solicitante1\":\"['tsp.samara.silva']\",\"tipo_de_solicita_o\":\"Solicitação\",\"categoria\":\"Comprador/Pedido\",\"solicita_o_comprador_pedido\":\"Reembolso de Boleto - BILETO\",\"nome_do_evento\":\"BEETLEJUICE - O MUSICAL, O MUSICAL, O MUSICAL - Teatro Liberdade\",\"copy_of_n_do_pedido_obrigat_rio\":\"37920942\",\"explique_o_que_voc_precisa\":\"Reembolso, compra realizada via boleto\",\"attachment_t_tulo\":\"[]\",\"tipo_de_conta\":\"Conta-corrente\",\"nome_da_pessoa_titular_da_conta\":\"Flavelaine Olga de Lima\",\"cpf\":\"292.541.488-57\",\"banco\":\"Banco do Brasil\",\"c_digo_do_banco_n_o_obrigat_rio\":\"001\",\"ag_ncia_sem_digito_obrigat_rio\":\"2923\",\"conta_sem_digito_obrigat_rio\":\"32367\",\"digito_da_conta_obrigat_rio\":\"5\",\"valor_do_ingresso_sem_taxa\":\"380.00\",\"valor_taxa_sympla\":\"76.00\",\"valor_do_reembolso_obrigat_rio\":\"456.00\"},{\"solicitante1\":\"['tsp.samara.silva']\",\"tipo_de_solicita_o\":\"Solicitação\",\"categoria\":\"Comprador/Pedido\",\"solicita_o_comprador_pedido\":\"Reembolso de Boleto - BILETO\",\"nome_do_evento\":\"GOSTAVA MAIS DOS PAIS - Teatro Porto\",\"copy_of_n_do_pedido_obrigat_rio\":\"S38013066\",\"explique_o_que_voc_precisa\":\"Reembolso, compra realizada via boleto\",\"attachment_t_tulo\":\"[]\",\"tipo_de_conta\":\"Conta-corrente\",\"nome_da_pessoa_titular_da_conta\":\"Camila Duarte Guedes\",\"cpf\":\"269.209.068-30\",\"banco\":\"Itaú\",\"c_digo_do_banco_n_o_obrigat_rio\":\"341\",\"ag_ncia_sem_digito_obrigat_rio\":\"8754\",\"conta_sem_digito_obrigat_rio\":\"26576\",\"digito_da_conta_obrigat_rio\":\"2\",\"valor_do_ingresso_sem_taxa\":\"168.00\",\"valor_taxa_sympla\":\"25.20\",\"valor_do_reembolso_obrigat_rio\":\"193.20\"},{\"solicitante1\":\"['ALINE GUI']\",\"tipo_de_solicita_o\":\"Solicitação\",\"categoria\":\"Comprador/Pedido\",\"solicita_o_comprador_pedido\":\"Reembolso > 180 dias\",\"n_do_evento_obrigat_rio\":\"2071047\",\"copy_of_n_do_pedido_obrigat_rio\":\"1V6G762L8Q4\",\"explique_o_que_voc_precisa\":\"Oi pessoal, pedido com mais de 180 dias. \",\"attachment_t_tulo\":\"[]\",\"tipo_de_conta\":\"Conta-corrente\",\"nome_da_pessoa_titular_da_conta\":\"YAFA MIRIAM BENYOSEF\",\"cpf\":\"113.050.047-01\",\"banco\":\"C6 S.A.\",\"c_digo_do_banco_n_o_obrigat_rio\":\"336\",\"ag_ncia_sem_digito_obrigat_rio\":\"0001\",\"conta_sem_digito_obrigat_rio\":\"29096548\",\"digito_da_conta_obrigat_rio\":\"9\",\"valor_do_reembolso_obrigat_rio\":\"200.00\"},{\"solicitante1\":\"['Monica Re']\",\"tipo_de_solicita_o\":\"Solicitação\",\"categoria\":\"Comprador/Pedido\",\"solicita_o_comprador_pedido\":\"Falha Reembolso Cartão  - BILETO\",\"copy_of_n_do_pedido_obrigat_rio\":\"S35805875\",\"explique_o_que_voc_precisa\":\"Falha no reembolso;\",\"attachment_t_tulo\":\"[]\",\"tipo_de_conta\":\"Conta-corrente\",\"nome_da_pessoa_titular_da_conta\":\"Nathalia Cristina Correa Madeira\",\"cpf\":\"061.007.826-77\",\"banco\":\"Santander\",\"c_digo_do_banco_n_o_obrigat_rio\":\"033\",\"ag_ncia_sem_digito_obrigat_rio\":\"3154\",\"conta_sem_digito_obrigat_rio\":\"02010970\",\"digito_da_conta_obrigat_rio\":\"0\",\"valor_do_ingresso_sem_taxa\":\"260.00\",\"valor_taxa_sympla\":\"31.20\",\"valor_do_reembolso_obrigat_rio\":\"291.20\"},{\"solicitante1\":\"['ROSEMERE Si']\",\"tipo_de_solicita_o\":\"Solicitação\",\"categoria\":\"Comprador/Pedido\",\"solicita_o_comprador_pedido\":\"Falha Reembolso Cartão  - BILETO\",\"copy_of_n_do_pedido_obrigat_rio\":\"33122656\",\"explique_o_que_voc_precisa\":\"Pedido cancelado na bilheteria sem devolução. \",\"attachment_t_tulo\":\"[]\",\"tipo_de_conta\":\"Conta-corrente\",\"nome_da_pessoa_titular_da_conta\":\"Jamerson Goes Trezena\",\"cpf\":\"148.044.598-39\",\"banco\":\"Itau\",\"c_digo_do_banco_n_o_obrigat_rio\":\"341\",\"ag_ncia_sem_digito_obrigat_rio\":\"3767\",\"conta_sem_digito_obrigat_rio\":\"02767\",\"digito_da_conta_obrigat_rio\":\"9\",\"valor_do_ingresso_sem_taxa\":\"250.00\",\"valor_taxa_sympla\":\"0.00\",\"valor_do_reembolso_obrigat_rio\":\"250.00\"},{\"solicitante1\":\"['tsp.samara.silva']\",\"tipo_de_solicita_o\":\"Solicitação\",\"categoria\":\"Comprador/Pedido\",\"solicita_o_comprador_pedido\":\"Falha Reembolso Cartão  - BILETO\",\"copy_of_n_do_pedido_obrigat_rio\":\"S38016918\",\"explique_o_que_voc_precisa\":\"Reembolso, compra realizada via pix com falha no cancelamento\",\"attachment_t_tulo\":\"[]\",\"tipo_de_conta\":\"Conta-corrente\",\"nome_da_pessoa_titular_da_conta\":\"Íris Góis de Lima\",\"cpf\":\"506.583.438-58\",\"banco\":\"Nubank\",\"c_digo_do_banco_n_o_obrigat_rio\":\"260\",\"ag_ncia_sem_digito_obrigat_rio\":\"0001\",\"conta_sem_digito_obrigat_rio\":\"85042590\",\"digito_da_conta_obrigat_rio\":\"9\",\"valor_do_ingresso_sem_taxa\":\"90.00\",\"valor_do_reembolso_obrigat_rio\":\"90.00\"},{\"solicitante1\":\"['tsp.samara.silva']\",\"tipo_de_solicita_o\":\"Solicitação\",\"categoria\":\"Comprador/Pedido\",\"solicita_o_comprador_pedido\":\"Falha Reembolso Cartão  - BILETO\",\"copy_of_n_do_pedido_obrigat_rio\":\"S37796583\",\"explique_o_que_voc_precisa\":\"Reembolso, compra realizada via pix com falha no cancelamento\",\"attachment_t_tulo\":\"[]\",\"tipo_de_conta\":\"Conta-corrente\",\"nome_da_pessoa_titular_da_conta\":\"Jéssica Soares Rodrigues de Azevedo\",\"cpf\":\"134.483.397-76\",\"banco\":\"Santander\",\"c_digo_do_banco_n_o_obrigat_rio\":\"033\",\"ag_ncia_sem_digito_obrigat_rio\":\"3589\",\"conta_sem_digito_obrigat_rio\":\"02003213\",\"digito_da_conta_obrigat_rio\":\"4\",\"valor_do_ingresso_sem_taxa\":\"140.00\",\"valor_taxa_sympla\":\"16.80\",\"valor_do_reembolso_obrigat_rio\":\"156.80\"},{\"solicitante1\":\"['Victor Melo']\",\"tipo_de_solicita_o\":\"Solicitação\",\"categoria\":\"Organizador/Evento\",\"solicita_o_organizador_evento\":\"Habilitar login no contrato (TTRS)\",\"n_do_evento_obrigat_rio\":\"7619\",\"explique_o_que_voc_precisa\":\"Olá, precisamos liberar os seguintes endereços de e-mail para solicitação e aprovação de repasse: luan@lamkt.com.br e betobianco@gmail.com'''''\",\"anexar_contrato\":\"['https://app.pipefy.com/storage/v1/signed/uploads/f1e9731f-aabd-408b-bd9f-9266f6ed3b20/DocuSign_TTRS_e_SYMPLA__Contrato_de_Parceri14.pdf?expires_on=1709057849&signature=fBtVcgf2X8ghI6gXtSmDVk7u7pmczXIVsB5dOk3qGS4%3D']\"},{\"solicitante1\":\"['tsp.samara.silva']\",\"tipo_de_solicita_o\":\"Solicitação\",\"categoria\":\"Comprador/Pedido\",\"solicita_o_comprador_pedido\":\"Reembolso de Boleto - BILETO\",\"nome_do_evento\":\"JOÃO GOMES EM PORTO ALEGRE - 10/05/2024 - Araújo Vianna\",\"copy_of_n_do_pedido_obrigat_rio\":\"36980957\",\"explique_o_que_voc_precisa\":\"Reembolso, compra realizada via boleto\",\"attachment_t_tulo\":\"[]\",\"tipo_de_conta\":\"Conta-corrente\",\"nome_da_pessoa_titular_da_conta\":\"Jéssica Sabrina Schneider dos Santos\",\"cpf\":\"600.845.720-00\",\"banco\":\"Banco do Brasil\",\"c_digo_do_banco_n_o_obrigat_rio\":\"001\",\"ag_ncia_sem_digito_obrigat_rio\":\"628\",\"conta_sem_digito_obrigat_rio\":\"62.505\",\"digito_da_conta_obrigat_rio\":\"1\",\"valor_do_ingresso_sem_taxa\":\"75.00\",\"valor_taxa_sympla\":\"11.25\",\"valor_do_reembolso_obrigat_rio\":\"86.25\"}]";
        TableValue tb = jsn.action(jsonText);


    }


    public void test(){
        Table tb = this.tabela();
        RowCount a = new RowCount();
        ColCount b = new ColCount();

        boolean v = b.validate(tb,"=",3.0);

        System.out.println(v);

    }


    public void listToHeaders(){
        ListToHeaders a = new ListToHeaders();
        Table tb = this.tabela();

        ListValue<String> returnvalue = new ListValue<String>();
        List<Value> vals = new ArrayList<Value>();

        vals.add(new StringValue("OK"));
        vals.add(new StringValue("ABC"));
        vals.add(new StringValue("DEF"));

        tb = a.action(tb,vals).get();

        uteisTest.printTable(tb,10);

    }

    //@Test
    public void teste(){
        Calculate a = new Calculate();
        Table tb = this.tabela();

        NumberValue ret = a.action(tb,"USD|@123",code());
        System.out.println("==================" + ret.get());
    }

    //@Test
    public void HasHeaders(){
        HasHeader a = new HasHeader();

        Table tb = this.tabela();
        System.out.println(a.validate(tb,"TEST",false,true));

    }

    private Table tabela(){
        //TableValue tbv = new TableValue();
        Table searchResult = new Table();
        List<Schema> header = new ArrayList<Schema>();
        List<Row> rows = new ArrayList<Row>();
        List<Value> currentRow = new ArrayList<>();
        Row rw = new Row();

        //CRIA AS COLUNAS
        header.add(new Schema("TEST "));
        header.add(new Schema("USD"));
        header.add(new Schema("BRL"));
        searchResult.setSchema(header);

        //ADCIONA A LINHA
        currentRow.add(new StringValue("ROW1COL1"));
        currentRow.add(new StringValue("1456.25"));
        currentRow.add(new StringValue("12"));
        rw.setValues(currentRow);
        rows.add(rw);

        //SEGUNDA LINHA
        currentRow = new ArrayList<>();
        rw = new Row();
        currentRow.add(new StringValue("ROW2COL1"));
        currentRow.add(new StringValue("25.40"));
        currentRow.add(new StringValue(""));
        rw.setValues(currentRow);
        rows.add(rw);

        //TERCEIRA LINHA
        currentRow = new ArrayList<>();
        rw = new Row();
        currentRow.add(new StringValue("ROW3COL1"));
        currentRow.add(new StringValue("25.40"));
        currentRow.add(new StringValue("4.658,58"));
        rw.setValues(currentRow);
        rows.add(rw);

        searchResult.setRows(rows);
        return searchResult;
    }

    private String code(){
        try{
            BufferedReader reader = Files.newBufferedReader(Paths.get("C:/Temp/js.js"), StandardCharsets.UTF_8);
            StringWriter writer = new StringWriter();
            String code = "";
            String line;
            while ((line = reader.readLine()) != null) {
                code += line + "\n";
            }

            return code;
        }
        catch (Exception e){
            throw new BotCommandException("Error when trying to load Js code!");
        }
    }


}