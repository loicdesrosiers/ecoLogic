<?php

function choixAlert($message)
{
  $alert = array();
  switch($message)
  {
  case'delpasok':
    $alert['messageAlert'] = DELPASOK;
    break;
  case 'delok':
    $alert['messageAlert'] = DELOK;
    $alert['classAlert'] = 'success';
    break;
  case 'pb_select_qcm' :
    $alert['messageAlert'] = PB_SELECT_QCM;
    break;
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
  case 'questionfalse' :
    $alert['messageAlert'] = QUESTIONFALSE;
    break;
  case 'questiontrue' :
    $alert['messageAlert'] = QUESTIONTRUE;
    $alert['classAlert'] = 'success';
    break;
  case 'formvide' :
    $alert['messageAlert'] = FORMVIDE;
  default :
      $alert['messageAlert'] = MESSAGE_ERR;
  }
  return $alert;
}
