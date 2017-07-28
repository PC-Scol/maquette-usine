 import jenkins.model.*
        import com.cloudbees.plugins.credentials.*
        import com.cloudbees.plugins.credentials.common.*
        import com.cloudbees.plugins.credentials.domains.*
        import com.cloudbees.jenkins.plugins.sshcredentials.impl.*
        import hudson.plugins.sshslaves.*;

        global_domain = Domain.global()
        def hasCreds = com.cloudbees.plugins.credentials.CredentialsProvider.lookupCredentials(BasicSSHUserPrivateKey.class).findAll { it.id == "gitlab-ssh-key" }.size();
        if (!hasCreds) {
            credentials_store =
            Jenkins.instance.getExtensionList(
                'com.cloudbees.plugins.credentials.SystemCredentialsProvider'
            )[0].getStore()
    credentials = new BasicSSHUserPrivateKey(
            CredentialsScope.GLOBAL,
            "gitlab-ssh-key",
            "git",
            new BasicSSHUserPrivateKey.FileOnMasterPrivateKeySource("/var/lib/jenkins/keys/ssh/id_rsa"),
            "",
            "Connexion SSH à GitLab"
            )
            credentials_store.addCredentials(global_domain, credentials)
        }