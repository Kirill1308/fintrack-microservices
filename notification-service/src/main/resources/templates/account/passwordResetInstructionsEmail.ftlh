<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Password Reset Request</title>
    <style>
        body.email-body {
            font-family: Arial, Helvetica, sans-serif;
        }

        .email-container {
            max-width: 600px;
            margin: 20px auto;
            background-color: #ffffff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .email-header {
            text-align: center;
            padding-bottom: 20px;
            border-bottom: 2px solid #f0f0f0;
            margin-bottom: 30px;
        }

        .email-title {
            color: #2c3e50 !important;
            font-size: 24px;
            margin: 0;
        }

        .email-content {
            margin-bottom: 30px;
        }

        .email-text {
            margin: 0 0 20px 0;
            font-size: 16px;
            color: #333333 !important;
        }

        .email-button {
            display: inline-block;
            padding: 12px 24px;
            background-color: #3498db;
            color: #ffffff !important;
            text-decoration: none;
            border-radius: 4px;
            margin: 20px 0;
            font-weight: bold;
            text-align: center;
        }

        .email-note {
            font-size: 14px;
            color: #7f8c8d;
            font-style: italic;
        }

        .email-footer {
            margin-top: 30px;
            padding-top: 20px;
            border-top: 2px solid #f0f0f0;
            font-size: 14px;
            color: #666666;
        }

        .email-contact {
            margin: 0;
        }

        .email-support-link {
            color: #3498db;
            text-decoration: none;
        }
    </style>
</head>
<body class="email-body">
<div class="email-container">
    <div class="email-header">
        <h1 class="email-title">Password Reset Request</h1>
    </div>

    <div class="email-content">
        <p class="email-text">
            We received a request to reset your password.
        </p>
        <a href="${resetLink}" class="email-button">Reset Password</a>
        <p class="email-note">
            If you did not request this change, you can safely ignore this email. The link will expire in 1 hour.
        </p>
    </div>

    <div class="email-footer">
        <p class="email-contact">
            If you have any questions, please <a href="mailto:support@example.com" class="email-support-link">contact our support team</a>.
        </p>
    </div>
</div>
</body>
</html>
