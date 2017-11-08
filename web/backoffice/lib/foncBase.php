<?php

function choixAlert($message)
{
  $alert = array();
  switch($message)
  {
  case 'url_non_valide' :
    $alert['messageAlert'] = MESSAGE_404;
    break;
	case 'connexion_succ' :
		$alert['messageAlert'] = MESSAGE_SUCC;
		$alert['classAlert'] = 'success';
		break;
  case 'connexion_err' :
    $alert['messageAlert'] = MESSAGE_ERR_CON;
    break;
  default :
      $alert['messageAlert'] = MESSAGE_ERR;
  }
  return $alert;
}
