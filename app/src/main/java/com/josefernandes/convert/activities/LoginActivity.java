package com.josefernandes.convert.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.josefernandes.convert.R;
import com.josefernandes.convert.config.ConfiguracaoFirebase;

public class LoginActivity extends AppCompatActivity {

    private SignInButton btnLogin;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference databaseReference;

    private static final int RC_SIGN_IN = 200;
    //private static final String TAG = "LOGIN_ACTIVITY";
    private TextView txtPrivacidade;
    private TextView txtVersao;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                    databaseReference = ConfiguracaoFirebase.getFirebase();
                    databaseReference.setValue(mAuth.getUid());
                    startActivity(new Intent(LoginActivity.this, ConvertActivity.class));
                    finish();
                }
            }
        };

        inicializarComponentes();
        mostraVersao();

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(LoginActivity.this, "Erro na conexão. Tente novamente mais tarde!", Toast.LENGTH_LONG).show();
                    }
                }).addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        txtPrivacidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostraPoliticaDePrivacidade();
            }
        });
    }

    private void inicializarComponentes(){
        txtPrivacidade = findViewById(R.id.login_txt_privacidade);
        btnLogin = findViewById(R.id.sign_in_button);
        txtVersao = findViewById(R.id.login_txt_versao_app);
    }

    private void mostraPoliticaDePrivacidade() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setIcon(android.R.drawable.ic_menu_info_details);
        builder.setTitle("Política de Privacidade");
        String mensagem = "SEÇÃO 1 - O QUE FAZEMOS COM AS SUAS INFORMAÇÕES?\n" +
                "\n" +
                "Quando você compra alguma coisa na nossa loja, como parte do processo de compra e venda, coletamos as informações pessoais que você nos fornece, tais como seu nome, endereço e e-mail.\n" +
                "\n" +
                "Quando você navega pela nossa loja, recebemos também automaticamente o protocolo de internet do seu computador (endereço de IP) a fim de obter informações que nos ajudam a saber mais sobre seu navegador e sistema operacional.\n" +
                "\n" +
                "Marketing por e-mail (quando aplicável): Com sua permissão, podemos lhe enviar e-mails sobre nossa loja, novos produtos e outras atualizações.\n" +
                "\n" +
                "\n" +
                "SEÇÃO 2 - CONSENTIMENTO\n" +
                "\n" +
                "Como vocês obtêm meu consentimento?\n" +
                "\n" +
                "Quando você nos fornece as suas informações pessoais para completar uma transação, verificar seu cartão de crédito, fazer um pedido, providenciar uma entrega ou retornar uma compra, você está concordando com a nossa coleta e uso de informações pessoais apenas para esses fins específicos.\n" +
                "\n" +
                "Se pedirmos suas informações pessoais por uma razão secundária, como marketing, vamos pedir seu consentimento, ou te dar a oportunidade de dizer não.\n" +
                "\n" +
                "\n" +
                "Como posso retirar o meu consentimento?\n" +
                "\n" +
                "Caso depois de fornecer seus dados você mude de ideia, você pode retirar o seu consentimento quando quiser e assim evitar que entremos em contato para obter ou divulgar informações. Entre em contato conosco através do josefsjunior94@gmail.com.\n" +
                "\n" +
                "\n" +
                "SEÇÃO 3 - DIVULGAÇÃO\n" +
                "\n" +
                "Podemos divulgar suas informações pessoais se formos obrigados por lei a fazê-lo ou se você violar nossos Termos de serviço.\n" +
                "\n" +
                "\n" +
                "SEÇÃO 4 - SERVIÇOS DE TERCEIROS\n" +
                "\n" +
                "\n" +
                "No geral, nossos fornecedores terceirizados irão coletar, usar e divulgar suas informações apenas na medida do necessário para permitir que eles realizem os serviços que eles nos fornecem.\n" +
                "\n" +
                "Entretanto, certos prestadores de serviços terceirizados, tais como gateways de pagamento e outros processadores de transações de pagamento, têm suas próprias políticas de privacidade em relação à informação que somos obrigados a fornecer para eles sobre transações relacionadas a compras.\n" +
                "\n" +
                "Para esses fornecedores, recomendamos que você leia suas políticas de privacidade para que você possa entender de que maneira suas informações pessoais serão usadas por esses fornecedores.\n" +
                "\n" +
                "Especificamente, lembre-se que certos fornecedores podem estar localizados ou possuir instalações que ficam em jurisdições diferentes da sua ou da nossa. Por isso, se você quiser continuar com uma transação que envolva um prestador de serviços terceirizado, suas informações podem ficar sujeitas à legislação da(s) jurisdição(ões) onde o prestador de serviços ou suas instalações estiverem localizados.\n" +
                "\n" +
                "Por exemplo, se você está no Canadá e sua transação é processada por um gateway de pagamento nos Estados Unidos, suas informações pessoais usadas para completar a transação podem estar sujeitas a divulgação sob a legislação dos Estados Unidos, incluindo a Lei Patriótica.\n" +
                "\n" +
                "Uma vez que você sair do site da nossa loja ou for redirecionado para um aplicativo ou site de terceiros, você não será mais regido por essa Política de privacidade ou pelos Termos de serviço do nosso site.\n" +
                "\n" +
                "\n" +
                "SEÇÃO 5 - SEGURANÇA\n" +
                "\n" +
                "Para proteger suas informações pessoais, tomamos precauções e seguimos as melhores práticas da indústria para nos certificar que elas não sejam perdidas, usurpadas, acessadas, divulgadas, alteradas ou destruídas.\n" +
                "\n" +
                "Se você nos fornecer as suas informações de cartão de crédito, elas serão criptografadas usando a tecnologia \"secure socket layer\" (SSL) e armazenadas com criptografia AES-256.  Embora nenhum método de transmissão pela Internet ou armazenamento eletrônico seja 100% seguro, nós seguimos todos os requisitos da PCI-DSS e implementamos medidas adicionais aceitas pelos padrões da indústria.\n" +
                "\n" +
                "\n" +
                "\n" +
                "SEÇÃO 6 - IDADE DE CONSENTIMENTO\n" +
                "\n" +
                "Ao usar esse site, você confirma que você é maior de idade em seu estado ou província de residência e que você nos deu seu consentimento para permitir que qualquer um dos seus dependentes menores de idade usem esse site.\n" +
                "\n" +
                "\n" +
                "SEÇÃO 7 - ALTERAÇÕES NA POLÍTICA DE PRIVACIDADE\n" +
                "\n" +
                "Reservamos o direito de modificar essa política de privacidade a qualquer momento. Portanto, por favor, leia-a com frequência. As alterações e esclarecimentos surtem efeito imediatamente após serem publicadas no site. Caso façamos alterações na política de privacidade, iremos notificá-lo sobre a atualização. Assim, você saberá quais informações coletamos, como as usamos, e sob que circunstâncias, caso aplicável, as utilizaremos e/ou divulgaremos.\n" +
                "\n" +
                "Caso ocorra a fusão da nossa loja com outra empresa, suas informações podem ser transferidas para os novos proprietários para que possamos continuar vendendo produtos para você.\n" +
                "\n" +
                "\n" +
                "PERGUNTAS E INFORMAÇÕES DE CONTATO\n" +
                "\n" +
                "Se você gostaria de acessar, corrigir, alterar ou excluir quaisquer informações pessoais que temos sobre você, registre uma queixa, ou simplesmente peça mais informações de contato a(o) nosso(a) Diretor(a) de privacidade através do josefsjunior94@gmail.com.\n" +
                "\n" +
                "Fernandes Labs.\n" +
                "\n" +
                "----";
        builder.setMessage(mensagem);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.setCancelable(false);
        builder.create();
        builder.show();
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        // Check if user is signed in (non-null) and update UI accordingly.
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {

            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        //Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        mostraBarraDeCarregamento();
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithCredential:success");
                            //FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                        dialog.dismiss();
                        // ...
                    }
                });
    }

    private void mostraBarraDeCarregamento(){
        dialog = new ProgressDialog(LoginActivity.this);
        dialog.setMessage("Entrando...");
        dialog.show();
    }

    private void mostraVersao() {
        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);

            String version = pInfo.versionName;
            //int verCode = pInfo.versionCode;
            //String autor = getString(R.string.autor);

            String montaTexto = "v" + version;
            txtVersao.setText(montaTexto);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
